import {
  Component, EventEmitter,
  Input,
  Output
} from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
})
export class UserListComponent {

  @Input() users: User[] = [];
  @Output() add = new EventEmitter<void>();
  @Output() edit = new EventEmitter<User>();

  displayedColumns: string[] = [
    'Mitgliedsnummer',
    'Name',
    'Mitgliedsart',
    'Aktionen',
  ];

  constructor() {}

  /**
   * Function that gets triggered by the Create-Button. Event is emitted to Parent.
   *
   * @Author: Luca Ulrich
   * @returns: void
   */
  onAdd(): void {
    this.add.emit();
  }

  /**
   * Function that gets triggered by the Edit-Button. Event is emitted to Parent.
   *
   * @Author: Luca Ulrich
   * @returns: void
   */
  onEdit(row: User): void {
    this.edit.emit(row);
  }
}
