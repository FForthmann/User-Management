import { Component, Input } from '@angular/core';
import { User } from '../../model/user';
import { MatTableDataSource } from '@angular/material/table';
import { Payment } from '../../model/payment';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styleUrls: ['./list-view.component.scss'],
})
export class ListViewComponent {
  columns: any = [];
  displayedColumns: any = [];
  dataSource: MatTableDataSource<User | Payment> = new MatTableDataSource<User | Payment>();

  @Input() set setColumns(columns: any) {
    this.columns = columns;
    this.displayedColumns = columns.map((column: any) => column.columnDef);
    console.log(this.columns);
    console.log(this.displayedColumns);
  }
  @Input() set setInput(input: User[] | Payment[]) {
    this.dataSource = new MatTableDataSource<User | Payment>(input);
  }

  constructor() {}
}
