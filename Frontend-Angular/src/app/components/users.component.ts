import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.reloadList();
  }

  saveUser(user: User) {
    this.userService.saveUser(user).subscribe(() => {
      this.reloadList();
    });
  }

  private reloadList(): void {
    this.userService.getUsers().subscribe((users: User[]) => {
      this.users = users;
    });
  }
}
