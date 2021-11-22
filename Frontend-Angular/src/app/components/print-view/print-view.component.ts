import { Component, Input } from '@angular/core';
import { Payment } from '../../model/payment';
import { User } from '../../model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/users/user.service';
import { PaymentService } from '../../services/payments/payment.service';
import { jsPDF } from 'jspdf';
import { UserOptions } from 'jspdf-autotable';
import 'jspdf-autotable';

interface jsPDFCustom extends jsPDF {
  autoTable: (options: UserOptions) => void;
}

@Component({
  selector: 'app-print-view',
  templateUrl: './print-view.component.html',
  styleUrls: ['./print-view.component.scss'],
})
export class PrintViewComponent {
  doc: jsPDFCustom = new jsPDF() as jsPDFCustom;
  year: string = '';
  route: string = this.router.url;

  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private userService: UserService,
    private paymentService: PaymentService
  ) {
    if (activeRoute.snapshot.params.year) {
      this.year = activeRoute.snapshot.params.year;
      this.route = this.router.url.substring(0, this.router.url.lastIndexOf('/'));
    }
    // Checks the active url for active Route
    switch (this.route) {
      case '/users/print':
        this.getData('users');
        break;
      case '/payments/print':
        this.getData('payments');
        break;
      default:
        console.log('DEFAULTED');
    }
  }

  /**
   * Function to build a PDF for Users
   *
   * @author Luca Ulrich
   * @contributor Jan Ramm
   * @param {User[]} user
   * @private
   */
  private buildUserPDF(user: User[]): void {
    console.log(user);
    let buildUserData: Array<Array<string | number>> = [];
    user.forEach((res: User) => {
      let tempData: Array<string | number> = [];
      res.userId ? tempData.push(res.userId) : '';
      tempData.push(res.name.firstName);
      tempData.push(res.name.lastName);
      tempData.push(res.entryDate);
      tempData.push(res.description);
      // res.actualAmount ? tempData.push(res.actualAmount) : '';
      buildUserData.push(tempData);
    });
    this.doc.autoTable({
      head: [['MitgliedsID', 'Name', 'Nachname', 'Eintrittsdatum', 'Mitgliedsart', 'Aktueller Beitrag']],
      body: buildUserData,
    });
    this.doc.save(`jahresabschluss_mitglieder_${this.year}.pdf`);
  }

  /**
   * Function to build a PDF for Payments
   *
   * @author Luca Ulrich & Jan Ramm
   * @param {Payment[]} payments
   * @private
   */
  private buildPaymentPDF(payments: Payment[]): void {
    console.log(payments);
    let buildPaymentData: Array<Array<string | number>> = [];
    payments.forEach((res: Payment) => {
      let tempData: Array<string | number> = [];
      tempData.push(res.invoiceNumber);
      tempData.push(res.year);
      tempData.push(res.bankAccountDetails);
      tempData.push(res.amount);
      res.countStatus ? tempData.push('bezahlt') : tempData.push('nicht bezahlt');
      buildPaymentData.push(tempData);
    });
    this.doc.autoTable({
      head: [['Rechnungsnummer', 'Jahr', 'Bankdaten', 'Rechnungsbetrag', 'Zahlstatus']],
      body: buildPaymentData,
    });
    this.doc.save(`jahresabschluss_rechnungen_${this.year}.pdf`);
  }

  /**
   * Function to get the Data from the Services. It checks whether the active route is /users/print or /payments/print.
   * If a year is passed as a parameter in the URL, only the data matching the URL will be processed.
   *
   * @author Luca Ulrich
   * @param {string} type - users or payments
   * @private
   * @returns {void}
   */
  private getData(type: string): void {
    if (type === 'users') {
      this.userService
        .getUsers()
        .pipe()
        .subscribe((users: User[]) => {
          if (this.year) {
            let foundUsers: User[] = [];
            users.forEach((user: User) => {
              if (user.entryDate.split('-')[0] === this.year) {
                foundUsers.push(user);
              }
            });
            this.buildUserPDF(foundUsers);
          } else {
            this.buildUserPDF(users);
          }
        });
    } else if (type === 'payments') {
      this.paymentService.getPayments().subscribe((payments: Payment[]) => {
        if (this.year) {
          let foundPayments: Payment[] = [];
          payments.forEach((payment: Payment) => {
            if (payment.year === parseInt(this.year)) {
              foundPayments.push(payment);
            }
          });
          this.buildPaymentPDF(foundPayments);
        } else {
          this.buildPaymentPDF(payments);
        }
      });
    }
  }
}
