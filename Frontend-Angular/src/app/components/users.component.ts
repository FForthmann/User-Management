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

  /**
   * Function to communicate with the userService to save a User.
   *
   * @Author: Luca Ulrich
   * @param user
   * @returns: void
   */
  saveUser(user: User): void {
    this.userService.saveUser(user).subscribe(() => {
      this.reloadList();
    });
  }

  /**
   * Function that gets called by Child-Modal.
   * It initializes the Form and sets up the child-Modal. Also awaits the closing Dialog to do something with
   * the data.
   *
   * @Author: Luca Ulrich
   * @returns: void
   */
  onAddUser(): void {
    this.userService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";

    const dialogRef = this.dialog.open(UserFormComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      this.saveUser(result);
    })

  }

  /**
   * Function to reload UserData
   *
   * @Author: Luca Ulrich
   * @private
   * @returns: void
   */
  private reloadList(): void {
    this.userService.getUsers().subscribe((users: User[]) => {
      this.users = users;
    });
  }


}
