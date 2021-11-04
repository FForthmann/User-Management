import {Component, Inject} from '@angular/core';
import {UserService} from "../../services/user.service";
import {formUser, saveUser, User} from "../../model/user";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})

export class UserFormComponent {

  //@TODO: Refactor userService - maybe needs a fromService
  constructor(private datePipe: DatePipe,
              public userService: UserService,
              public dialogRef: MatDialogRef<UserFormComponent>,
              @Inject(MAT_DIALOG_DATA) public data: User) { }

  /**
   * Function to close the Modal and send Data to Parent-Class on Eventcall
   *
   * @Author: Luca Ulrich
   * @param event: string
   * @returns: void
   */
  closeDialog(event: string): void {
    this.dialogRef.close({ event: event, data:this.buildUser(this.userService.form.value)});
    this.userService.form.reset();
    this.userService.initializeFormGroup();
  }

  /**
   * Helper-Function to build a User Object to pass to other functions
   *
   * @Author: Luca Ulrich
   * @param userData
   * @returns: User Object
   * @TODO: Build a formUser Model or refactor this
   */
  private buildUser(userData: formUser): saveUser {
    return {
      name: {
        firstName: userData.firstName,
        lastName: userData.lastName
      },
      address: {
        street: userData.street,
        houseNumber: parseInt(userData.houseNumber),
        postalCode: parseInt(userData.postalCode),
        city: userData.city
      },
      birthday: (this.datePipe.transform(userData.birthday, 'yyyy-MM-dd') as string),
      entryDate: (this.datePipe.transform(userData.entryDate, 'yyyy-MM-dd') as string),
      cancellationDate: "" ? "": (this.datePipe.transform(userData.cancellationDate, 'yyyy-MM-dd') as string),
      leavingDate: "" ? "": (this.datePipe.transform(userData.leavingDate, 'yyyy-MM-dd') as string),
      memberType: userData.memberType,
      accountDetails: userData.accountDetails,
      familyId: userData.familyId? parseInt(userData.familyId): undefined
    }
  }
}
