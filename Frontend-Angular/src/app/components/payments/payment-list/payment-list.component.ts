import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Payment } from '../../../model/payment';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss'],
})
export class PaymentListComponent {
  /** @type{MatTableDataSource<Payment>} */
  dataSource: MatTableDataSource<Payment> = new MatTableDataSource<Payment>();
  /** @type {string[]} */
  displayedColumns: string[] = ['invoiceNumber', 'year', 'bankAccountDetails', 'amount', 'countStatus'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  @Input() set tableSource(value: Payment[]) {
    this.dataSource = new MatTableDataSource(value);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  @Output() edit: EventEmitter<number> = new EventEmitter<number>();

  constructor() {}

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

  /**
   * Function to Apply Filter on Datatable Source
   *
   * @author Luca Ulrich
   * @param {KeyboardEvent} event - event that gets emitted while writing in Table Search
   * @returns {void}
   */
  applyFilter(event: KeyboardEvent): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
