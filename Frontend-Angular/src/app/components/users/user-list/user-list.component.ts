import { Component, Input } from '@angular/core';
import { User } from '../../../model/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {

  @Input() users: User[] = [];

  /** @type {string[]} */
  displayedColumns: string[] = [
    'Mitgliedsnummer',
    'Name',
    'Mitgliedsart',
    'Aktionen'
  ];

  constructor() {
  }
}
