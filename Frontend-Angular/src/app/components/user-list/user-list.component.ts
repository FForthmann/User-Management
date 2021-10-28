import {Component, Input, OnInit} from '@angular/core';
import { User } from "../../model/user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  @Input() users: User[] = [];

  displayedColumns: string[] = ['Mitgliedsnummer','Name','Mitgliedsart','Aktionen'];
  dataSource: User[] = [];

  constructor() {}

  ngOnInit(): void {
    this.dataSource = this.users;
  }

  onEdit(row: unknown): void {

  }
}
