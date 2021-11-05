import { Component, OnInit } from '@angular/core';
import {Payment} from "../../model/payment";
import {PaymentService} from "../../services/payments/payment.service";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.scss']
})
export class PaymentsComponent implements OnInit {
  payments: Payment[] = [];

  constructor(private paymentService: PaymentService) { }

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
      this.payments = payments;
    });
  }

}
