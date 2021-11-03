import {Component, OnDestroy, OnInit} from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserFormComponent} from "./user-form/user-form.component";
import {ActivatedRoute, Router} from "@angular/router";
import {Subject} from "rxjs";
import {takeUntil} from "rxjs/operators";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit, OnDestroy {
  users: User[] = [];
  ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private userService: UserService,
              private dialog: MatDialog,
              private router: Router,
              private route: ActivatedRoute) {
    this.userService.modal.pipe(takeUntil(this.ngUnsubscribe)).subscribe( id => {
      if (id) {
        console.log('edit user with id ' + id);
        // make edit stuff with user id
        // check if really a number!!!
      } else {
        this.onAddUser();
      }
    });
  }

  ngOnInit(): void {
    this.reloadList();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
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

  editUser(user: User): void {
    this.userService.editUser(user).subscribe(() => {
      this.reloadList();
    });
  }


  //@TODO: Needs to be refactored
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
      this.router.navigate(['../'], { relativeTo: this.route });
      if( result.event === 'submit') {
        // Only saves Userdata if result.event is submit
        this.saveUser(result.data);
      }
    })
  }

  //@TODO: Needs to be refactored
  /**
   * Function to edit a User
   *
   * @Author: Luca Ulrich
   * @param user
   * @returns: void
   */
  onEditUser(user: User): void {
    this.userService.populateForm(user);

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";

    const dialogRef = this.dialog.open(UserFormComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      if( result.event === 'submit') {
        this.editUser(result.data);
      }
    })
  }

  /**
   * Helper-Function to reload UserData
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
