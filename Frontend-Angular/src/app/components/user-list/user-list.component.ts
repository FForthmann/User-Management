import {
  Component, EventEmitter,
  Input, Output,
} from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
})
export class UserListComponent {

  @Input() users: User[] = [];
  @Output() delete: EventEmitter<number> = new EventEmitter<number>();

  displayedColumns: string[] = [
    'Mitgliedsnummer',
    'Name',
    'Mitgliedsart',
    'Aktionen',
  ];

  constructor() {}

  deleteUser(userId: number) {
    this.delete.emit(userId);
  }
}
