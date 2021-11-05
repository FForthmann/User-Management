import {Component, Input, OnInit} from '@angular/core';
import {Payment} from "../../../model/payment";

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss']
})
export class PaymentListComponent {

  @Input() payments: Payment[] = [];
  displayedColumns: string[] = [
    'Rechnungsnummer',
    'Jahr',
    'Bankverbindung',
    'Status',
    'Aktionen'
  ];

  constructor() { }

}
