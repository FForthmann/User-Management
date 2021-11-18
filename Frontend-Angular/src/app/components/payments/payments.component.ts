import { Component, OnInit } from '@angular/core';
import {Payment} from "../../model/payment";
import {PaymentService} from "../../services/payments/payment.service";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmationDialogComponent} from "../confirmation-dialog/confirmation-dialog.component";
import {NotificationService} from "../../services/notifications/notification.service";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.scss']
})
export class PaymentsComponent implements OnInit {
  payments: Payment[] = [];

  constructor(private paymentService: PaymentService,
              private notificationService: NotificationService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.reloadList();
  }


  /**
   * Helper-Function to reload PaymentData
   *
   * @Author: Luca Ulrich
   * @private
   * @returns: void
   */
  private reloadList(): void {
    this.paymentService.getPayments().subscribe((payments: Payment[]) => {
      if (payments.length < 1) {
        this.notificationService.warn('Keine Rechnungen gefunden!');
      }
      this.payments = payments;
    },(message: string) => {
      this.notificationService.error(message);
    });
  }

  /**
   * Function to react on Child Payment-Status-Change-Event
   * Checks for current Payment-Status and changes it to the opposite
   *
   * @Author: Luca Ulrich
   * @param paymentId: number - ID of Payment
   * @returns: void
   */
  onPaymentEdit(paymentId: number): void {
    this.paymentService.getPayment(paymentId).subscribe((payment: Payment) => {
      if(payment) {
        let paymentStatus: string = 'bezahlt';
        if (payment.countStatus) {
          paymentStatus = ' nicht bezahlt';
          payment.countStatus = !payment.countStatus;
        } else {
          payment.countStatus = !payment.countStatus;
        }
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, {disableClose: false});

        dialogRef.componentInstance.confirmMessage = `Sind Sie sich sicher, dass Sie die Rechnung: ${payment.invoiceNumber}
        in einer Höhe von: ${payment.amount}€ als ${paymentStatus} setzen wollen?`;

        dialogRef.afterClosed().subscribe((result) => {
          if (result) {
            this.editPayment(payment);
          }
        });
      } else {
        this.notificationService.error(`Keinen Rechnung mit der Rechnungsnummer: ${paymentId} gefunden!`);
      }
    })
  }

  /**
   * Function to communicate with paymentService to edit Payment
   *
   * @Author: Luca Ulrich
   * @param payment: Payment - Payment to be edited
   * @returns: void
   */
  editPayment(payment: Payment): void {
    this.paymentService.editPayment(payment).subscribe(() => {
      if (payment.countStatus) {
        this.notificationService.success(`Die Rechnung:
        "${payment.invoiceNumber}" wurde als bezahlt markiert!`);
      } else {
        this.notificationService.warn(`Die Rechnung:
        "${payment.invoiceNumber}" wurde als nicht bezahlt markiert!`);
      }
      this.reloadList();
    },(message: string) => {
      this.notificationService.error(message);
    });
  }
}
