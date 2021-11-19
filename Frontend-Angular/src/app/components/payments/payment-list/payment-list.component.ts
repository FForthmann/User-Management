import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Payment} from "../../../model/payment";

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss']
})
export class PaymentListComponent {

  @Input() payments: Payment[] = [];
  @Output() edit: EventEmitter<number> = new EventEmitter<number>()

  /** @type {string[]} */
  displayedColumns: string[] = [
    'Rechnungsnummer',
    'Jahr',
    'Bankverbindung',
    'Beitrag',
    'Status'
  ];

  constructor() { }

  /**
   * Function to emit Payment-Status-Change to Parent Function
   *
   * @author Luca Ulrich
   * @param {number} paymentId - ID/InvoiceNumber of current Payment
   * @returns {void}
   */
  changePaymentStatus(paymentId: number): void {
    this.edit.emit(paymentId);
  }
}
