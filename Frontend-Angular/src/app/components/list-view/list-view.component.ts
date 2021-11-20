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
    if (input != null) {
      this.dataSource = new MatTableDataSource<User | Payment>(input);
      this.checkType(input[0]);
      this.dataSource.paginator = this.paginator;

      // Overrides the default Angular Filter, so it is able to reach deeper levels of nested Objects
      this.dataSource.filterPredicate = (data: User | Payment, filter: string) => {
        const dataStr = JSON.stringify(data).toLowerCase();
        return dataStr.indexOf(filter) != -1;
      };

      this.dataSource.sortingDataAccessor = this.pathDataAccessor;
      this.dataSource.sort = this.sort;
    }
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
      if ('invoiceNumber' in input) {
        if (!this.displayedColumns.includes('countStatus')) {
          this.displayedColumns.push('countStatus');
        }
        this.type = 'Payment';
      } else if ('userId' in input) {
        if (!this.displayedColumns.includes('actions')) {
          this.displayedColumns.push('actions');
        }
        this.type = 'User';
      }
    }
  }

  /**
   * Helper-Function to make sorting able to reach deeper levels of nested Object.
   *
   * @author Luca Ulrich
   * @reference: https://material.angular.io/components/table/api
   * @param {User | Payment} item
   * @param {string} path
   * @private
   * @returns {string}
   */
  private pathDataAccessor(item: User | Payment, path: string): string {
    return path.split('.').reduce((accu: any, key: string) => {
      return accu ? accu[key] : undefined;
    }, item);
  }
}
