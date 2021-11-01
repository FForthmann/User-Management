import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserFormComponent} from "./user-form/user-form.component";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService,
              private dialog: MatDialog) {}

  ngOnInit(): void {
    this.reloadList();
  }

  saveUser(user: User) {
    this.userService.saveUser(user).subscribe(() => {
      this.reloadList();
    });
  }

  onAddUser() {
    this.userService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";

    const dialogRef = this.dialog.open(UserFormComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      this.saveUser(result);
      console.log(result);
    })

  }

  private reloadList(): void {
    this.userService.getUsers().subscribe((users: User[]) => {
      this.users = users;
    });
  }


}
