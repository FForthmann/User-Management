import { Component, OnDestroy, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { UserService } from '../../services/users/user.service';
import { NotificationService } from '../../services/notifications/notification.service';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { UserFormComponent } from './user-form/user-form.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { FormService } from '../../services/form/form.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { Column } from '../../model/column';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit, OnDestroy {
  /** @type {User[]} */
  users: User[] = [];
  /** @type {Column[]} */
  columns: Column[] = [];
  /** @type {Subject<void>} */
  ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(
    private userService: UserService,
    private formService: FormService,
    private notificationService: NotificationService,
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.formService.modal
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((obj: { id: number | undefined; action: string }) => {
        if (obj.id) {
          if (obj.action === 'edit') {
            this.formService.triggerAccessibility(true);
            this.onEditUser(obj.id);
          } else if (obj.action === 'delete') {
            this.onDeleteUser(obj.id);
          }
        } else {
          this.formService.triggerAccessibility(false);
          this.onAddUser();
        }
      });
  }

  ngOnInit(): void {
    this.reloadList();
    this.buildColumns();
  }

  ngOnDestroy() {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

  /**
   * Function to communicate with the userService to save a User.
   *
   * @author Luca Ulrich
   * @param {User} user - User Objet to be saved
   * @returns {void}
   */
  saveUser(user: User): void {
    this.userService.saveUser(user).subscribe(
      () => {
        this.notificationService.success(`Der Nutzer:
        "${user.name.firstName + ' ' + user.name.lastName}" wurde erfolgreich angelegt!`);
        this.reloadList();
      },
      (message: string) => {
        this.notificationService.error(message);
        this.onAddUser(user);
      }
    );
  }

  /**
   * Function to communicate with the userService to edit a User.
   *
   * @author Luca Ulrich
   * @param {User} user - User Object to edit user
   * @returns {void}
   */
  editUser(user: User): void {
    this.userService.editUser(user).subscribe(
      () => {
        this.notificationService.success(`Der Nutzer:
        "${user.name.firstName + ' ' + user.name.lastName}" wurde erfolgreich editiert!`);
        this.reloadList();
      },
      (message: string) => {
        this.notificationService.error(message);
        this.onEditUser(user.userId);
      }
    );
  }

  /**
   * Function to communicate with the userService to delete a User.
   *
   * @author Luca Ulrich
   * @param {User} user - User Object to delete user
   * @returns {void}
   */
  deleteUser(user: User): void {
    this.userService.deleteUser(user.userId!).subscribe(
      () => {
        this.notificationService.success(`Der Nutzer:
        "${user.name.firstName + ' ' + user.name.lastName}" wurde erfolgreich gel??scht!`);
        this.reloadList();
      },
      (message: string) => {
        this.notificationService.error(message);
      }
    );
  }

  /**
   * Function to save a User
   * It initializes the Form and sets up the child-Modal. Also subscribes to the closing Dialog to perform actions on
   * the data.
   *
   * @author Luca Ulrich
   * @returns {void}
   */
  onAddUser(user?: User): void {
    user ? this.formService.initializeFormGroup(user) : this.formService.initializeFormGroup();

    const dialogRef = this.openFormModal();
    dialogRef.afterClosed().subscribe((result) => {
      this.router.navigate(['../'], { relativeTo: this.route });
      if (result.event === 'submit') {
        delete result.data['memberTypeChange']; //delete Key on creation
        this.saveUser(result.data);
      }
    });
  }

  /**
   * Function to edit a User
   * It initializes the Form and sets up the child-Modal. Also subscribes to the closing Dialog to perform actions with
   * the data.
   *
   * @author Luca Ulrich
   * @param {number} id - ID to get the specific User data
   * @returns {void}
   */
  onEditUser(id: number | undefined): void {
    if (id) {
      this.userService.getUser(id).subscribe((user: User) => {
        if (user) {
          this.formService.initializeFormGroup(user);
          const dialogRef = this.openFormModal();
          dialogRef.afterClosed().subscribe((result) => {
            this.router.navigate(['../'], { relativeTo: this.route });
            if (result.event === 'submit') {
              result.data['userId'] = user.userId;
              if (result.data['description'] === user.description) {
                delete result.data['memberTypeChange']; //delete key if no change in memberType
              }
              result.data['description'] = user.description; //reassign description, to save old description
              this.editUser(result.data);
            }
          });
        } else {
          this.notificationService.error(`Keinen Nutzer mit der Mitgliedsnummer: ${id} gefunden!`);
        }
      });
    } else {
      this.notificationService.error('Es ist ein unbekannter Fehler aufgetreten!');
    }
  }

  /**
   * Function to react to onDelete Event.
   * It opens a confirmation Dialog awaiting User-Input. If the User confirms the action a delete Event will be
   * triggered. Otherwise the delete action will be canceled.
   *
   * @author Luca Ulrich
   * @param {number} id - UserID to be deleted
   * @returns {void}
   */
  onDeleteUser(id: number): void {
    this.userService.getUser(id).subscribe((user: User) => {
      if (user) {
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, { disableClose: false });

        dialogRef.componentInstance.confirmMessage = `Sind Sie sich sicher, dass sie den Nutzer: ${user.name.firstName}
        ${user.name.lastName} mit der Mitgliedsnummer: ${user.userId} l??schen wollen?`;

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
   * @author Luca Ulrich
   * @private
   * @returns {MatDialogRef<UserFormComponent>}
   */
  private openFormModal(): MatDialogRef<UserFormComponent> {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';

    return this.dialog.open(UserFormComponent, dialogConfig);
  }

  /**
   * Helper-Function to reload UserData
   *
   * @author Luca Ulrich
   * @private
   * @returns {void}
   */
  private reloadList(): void {
    this.userService.getUsers().subscribe(
      (users: User[]) => {
        if (users.length < 1) {
          this.notificationService.warn('Keine Nutzerdaten gefunden!');
        }
        this.users = users;
      },
      (message: string) => {
        this.notificationService.error(message);
      }
    );
  }

  /**
   * Helper-Function to build Columns for the List-View Component
   *
   * @author Luca Ulrich
   * @private
   * @returns {void}
   */
  private buildColumns(): void {
    if (this.users) {
      this.columns = [
        {
          columnDef: 'userId',
          header: 'Mitgliedsnummer',
          cell: (user: User) => `${user.userId}`,
        },
        {
          columnDef: 'name.firstName',
          header: 'Vorname',
          cell: (user: User) => `${user.name.firstName}`,
        },
        {
          columnDef: 'name.lastName',
          header: 'Nachname',
          cell: (user: User) => `${user.name.lastName}`,
        },
        {
          columnDef: 'entryDate',
          header: 'Eintrittsdatum',
          cell: (user: User) => `${user.entryDate}`,
        },
        {
          columnDef: 'description',
          header: 'Mitgliedsart',
          cell: (user: User) => `${user.description}`,
        },
        {
          columnDef: 'actualAmount',
          header: 'Beitrag',
          cell: (user: User) => `${user.actualAmount}`,
        },
      ];
    }
  }
}
