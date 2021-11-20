import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { User } from '../../model/user';
import { MatTableDataSource } from '@angular/material/table';
import { Payment } from '../../model/payment';
import { Column } from '../../model/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styleUrls: ['./list-view.component.scss'],
})
export class ListViewComponent {
  /** @type {string} */
  type?: string;
  /** @type{Column[]} */
  columns: Column[] = [];
  /** @type {string[]} */
  displayedColumns: string[] = [];
  /** @type {MatTableDataSource<User|Payment>} */
  dataSource: MatTableDataSource<User | Payment> = new MatTableDataSource<User | Payment>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @Input() set setColumns(columns: Column[]) {
    this.columns = columns;
    this.displayedColumns = columns.map((column: Column) => column.columnDef);
  }
  @Input() set setInput(input: User[] | Payment[]) {
    this.dataSource = new MatTableDataSource<User | Payment>(input);
    this.checkType(input[0]);
    this.dataSource.paginator = this.paginator;
  }
  @Output() edit: EventEmitter<number> = new EventEmitter<number>();

  constructor() {}

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
   * Helper-Function that checks whether the 'this.input' variable has the Type <User> or <Payment>.
   * Depending on the type, actions regarding to the type are displayed.
   *
   * @author Luca Ulrich
   * @param {User | Payment} input - input Variable that gets passed into the Function
   * @private
   * @returns {void}
   */
  private checkType(input: User | Payment): void {
    if (input != null) {
      if (!this.displayedColumns.includes('actions')) {
        this.displayedColumns.push('actions');
      }
      if ('invoiceNumber' in input) {
        this.type = 'Payment';
      } else if ('userId' in input) {
        this.type = 'User';
      }
    }
  }
}
