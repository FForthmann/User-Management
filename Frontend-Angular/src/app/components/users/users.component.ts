import {Component, OnDestroy, OnInit} from '@angular/core';
import { User } from '../../model/user';
import { UserService } from '../../services/users/user.service';
import {NotificationService} from "../../services/notifications/notification.service";
import {MatDialog, MatDialogConfig, MatDialogRef} from "@angular/material/dialog";
import {UserFormComponent} from "./user-form/user-form.component";
import {ActivatedRoute, Router} from "@angular/router";
import {Subject} from "rxjs";
import {takeUntil} from "rxjs/operators";
import {FormService} from "../../services/form/form.service";
import {ConfirmationDialogComponent} from "../confirmation-dialog/confirmation-dialog.component";

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
              private notificationService: NotificationService,
              private dialog: MatDialog,
              private router: Router,
              private route: ActivatedRoute) {
    this.formService.modal.pipe(takeUntil(this.ngUnsubscribe)).subscribe( (obj: {id: number |undefined, action:string}) => {
      if (obj.id) {
        if(obj.action === 'edit') {
          this.onEditUser(obj.id);
        } else if (obj.action === 'delete') {
          this.onDeleteUser(obj.id);
        }
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
      this.notificationService.success(`Der Nutzer:
        "${user.name.firstName + ' '+ user.name.lastName}" wurde erfolgreich angelegt!`)
      this.reloadList();
    },(message: string) => {
      this.notificationService.error(message);
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
      this.notificationService.success(`Der Nutzer:
        "${user.name.firstName + ' '+ user.name.lastName}" wurde erfolgreich editiert!`)
      this.reloadList();
    },(message: string) => {
      this.notificationService.error(message);
    });
  }

  /**
   * Function to communicate with the userService to delete a User.
   *
   * @Author: Luca Ulrich
   * @param user - User Object to delete user
   * @returns: void
   */
  deleteUser(user: User): void {
    this.userService.deleteUser(user.userId!).subscribe(() => {
      this.notificationService.success(`Der Nutzer:
        "${user.name.firstName + ' '+ user.name.lastName}" wurde erfolgreich gelöscht!`)
      this.reloadList();
    },(message: string) => {
      this.notificationService.error(message);
    })
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
    this.formService.triggerAccessibility(false);
    this.formService.initializeFormGroup();
    const dialogRef = this.openFormModal();
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
    this.formService.triggerAccessibility(true);
    this.userService.getUser(id).subscribe((user: User) => {
      if(user) {
      this.formService.initializeFormGroup(user);
      const dialogRef = this.openFormModal();
      dialogRef.afterClosed().subscribe((result) => {
        this.router.navigate(['../'], { relativeTo: this.route });
        if (result.event === 'submit') {
          result.data['userId'] = user.userId;
          this.editUser(result.data);
        }
      })
    } else {
        this.notificationService.error(`Keinen Nutzer mit der Mitgliedsnummer: ${id} gefunden!`);
      }});
  }

  /**
   * Function to react to onDelete Event
   *
   * @Author: Luca Ulrich
   * @param id: number - UserID to be deleted
   * @returns: void
   */
  onDeleteUser(id: number): void {
    this.userService.getUser(id).subscribe((user: User) => {
      if(user) {
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, {disableClose: false});

        dialogRef.componentInstance.confirmMessage = `Sind Sie sich sicher, dass sie den Nutzer: ${user.name.firstName}
        ${user.name.lastName} mit der Mitgliedsnummer: ${user.userId} löschen wollen?`;

        dialogRef.afterClosed().subscribe((result) => {
          this.router.navigate(['../'], { relativeTo: this.route });
          if (result) {
            this.deleteUser(user);
          }
        });
      } else {
        this.notificationService.error(`Keinen Nutzer mit der Mitgliedsnummer: ${id} gefunden!`);
      }
    });
  }

  /**
   * Helper-Function to open a Form-Modal with defined Settings
   *
   * @Author: Luca Ulrich
   * @private
   * @returns: MatDialogRef<UserFormComponent>
   */
  private openFormModal(): MatDialogRef<UserFormComponent> {
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
      if(users.length < 1) {
        this.notificationService.warn('Keine Nutzerdaten gefunden!');
      }
      this.users = users;
    },(message: string) => {
      this.notificationService.error(message);
    });
  }
}
