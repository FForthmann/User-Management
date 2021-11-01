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

  displayedColumns: string[] = [
    'Mitgliedsnummer',
    'Name',
    'Mitgliedsart',
    'Aktionen',
  ];

  constructor() {}

  onEdit(row: unknown): void {}

  onAdd(): void {
    this.add.emit();
  }
}
