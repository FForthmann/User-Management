import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent {

  //@Output() save = new EventEmitter<User>();

  constructor(public userService: UserService,
              public dialogRef: MatDialogRef<UserFormComponent>,
              @Inject(MAT_DIALOG_DATA) public data: User) { }

  /**
   * Function to pass Event to Parent Function (via Emit EventHandler)
   *
   * @Author: Luca Ulrich
   * @returns: void
   *
   */
  onSubmit(): void {
    //this.save.emit(this.buildUser(this.userService.form.value))
    this.closeDialog();
  }

  private closeDialog() {
    console.log('Closing Dialog');
    this.userService.form.reset();
    this.userService.initializeFormGroup();
    this.dialogRef.close(this.buildUser(this.userService.form.value));
  }

  /**
   * Helper-Function to build a User Object to pass to other functions
   *
   * @Author: Luca Ulrich
   * @param userData
   * @returns: User Object
   * @TODO: Build a formUser Model or refactor this
   */
  private buildUser(userData: any): User {
    return {
      userId: 0,
      name: {
        firstName: userData.firstName,
        lastName: userData.lastName
      },
      address: {
        street: userData.street,
        houseNumber: userData.houseNumber,
        postalCode: userData.postalCode,
        city: userData.city
      },
      birthday : new Date(userData.birthday),
      entryDate : new Date(userData.entryDate),
      memberType : userData.memberType,
      accountDetails : userData.accountDetails
    }
  }
}
