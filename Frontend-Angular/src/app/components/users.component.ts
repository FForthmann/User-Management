import {Component, OnDestroy, OnInit} from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../services/user.service';
import {MatDialog, MatDialogConfig, MatDialogRef} from "@angular/material/dialog";
import {UserFormComponent} from "./user-form/user-form.component";
import {ActivatedRoute, Router} from "@angular/router";
import {Subject} from "rxjs";
import {takeUntil} from "rxjs/operators";
import {FormService} from "../services/form.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit, OnDestroy {
  users: User[] = [];
  ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private userService: UserService,
              private formService: FormService,
              private dialog: MatDialog,
              private router: Router,
              private route: ActivatedRoute) {
    this.formService.modal.pipe(takeUntil(this.ngUnsubscribe)).subscribe( (id: number | undefined) => {
      if (id) {
        if(!isNaN(id)) {
          this.onEditUser(id);
        }
      } else {
         this.onAddUser()
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

  /**
   * Function to communicate with the userService to edit a User.
   *
   * @Author: Luca Ulrich
   * @param user - User Object to edit user
   * @returns: void
   */
  editUser(user: User): void {
    this.userService.editUser(user).subscribe(() => {
      this.reloadList();
    });
  }

  /**
   * Function to save a User
   * It initializes the Form and sets up the child-Modal. Also subscribes to the closing Dialog to do something with
   * the data.
   *
   * @Author: Luca Ulrich
   * @returns: void
   */
  onAddUser(): void {
    this.formService.initializeFormGroup();
    const dialogRef = this.openModal();
    dialogRef.afterClosed().subscribe((result) => {
      this.router.navigate(['../'], { relativeTo: this.route });
      if( result.event === 'submit') {
        this.saveUser(result.data);
      }
    })
  }

  /**
   * Function to edit a User
   * It initializes the Form and sets up the child-Modal. Also subscribes to the closing Dialog to do something with
   * the data.
   *
   * @Author: Luca Ulrich
   * @param id: number - ID to get the specific User
   * @returns: void
   */
  onEditUser(id: number): void {
    this.userService.getUser(id).subscribe((user: User) => {
      this.formService.initializeFormGroup(user);
      const dialogRef = this.openModal();
      dialogRef.afterClosed().subscribe((result) => {
        this.router.navigate(['../'], { relativeTo: this.route });
        if (result.event === 'submit') {
          result.data['userId'] = user.userId;
          this.editUser(result.data);
        }
      })
    });
  }

  /**
   * Helper-Function to open a Modal with defined Settings
   *
   * @Author: Luca
   * @private
   * @returns: MatDialogRef<UserFormComponent>
   */
  private openModal(): MatDialogRef<UserFormComponent> {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";

    return this.dialog.open(UserFormComponent, dialogConfig);
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
