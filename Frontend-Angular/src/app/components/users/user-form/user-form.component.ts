import {Component, Inject, OnInit} from '@angular/core';
import {formUser, User} from "../../../model/user";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DatePipe} from "@angular/common";
import {FormService} from "../../../services/form/form.service";
import {MemberType} from "../../../model/memberType";
import {MembertypeService} from "../../../services/memberTypes/membertype.service";
import {MatDatepickerInputEvent} from "@angular/material/datepicker";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})

export class UserFormComponent implements OnInit{

  breakpoint: number = 1;
  memberTypes: MemberType[] = [];
  leavingDate: string = '';

  constructor(private datePipe: DatePipe,
              private memberTypeService: MembertypeService,
              public formService: FormService,
              public dialogRef: MatDialogRef<UserFormComponent>,
              @Inject(MAT_DIALOG_DATA) public data: User) {}


  ngOnInit() {
    this.memberTypeService.getMemberTypes().subscribe((memberTypes: MemberType[]) => {
      this.memberTypes = memberTypes;
    });
    this.breakpoint = (window.innerWidth <= 480) ? 1 : 6;
  }


  /**
   * Function to trigger Flex-Box on Window-size
   *
   * @Author: Jan Ram & Luca Ulrich
   * @param event: UIEvent
   * @returns: void
   */
  onResize(event: UIEvent): void {
    const target = event.target as Window;
    this.breakpoint = (target.innerWidth <= 480) ? 1 : 6;
  }

  /**
   * Function to close the Modal and send Data to Parent-Class
   *
   * @Author: Luca Ulrich
   * @param event: string
   * @returns: void
   */
  closeDialog(event: string): void {
    this.dialogRef.close({ event: event, data:this.buildUser(this.formService.form.value)});
    this.formService.form.reset();
    this.formService.initializeFormGroup();
  }

  /**
   * Helper-Function to build a User Object to pass to other functions
   *
   * @Author: Luca Ulrich
   * @param userData: formUser
   * @returns: User Object
   */
  private buildUser(userData: formUser): User {
    let leavingDate: Date = new Date();
    let userObject: User = {
      name: {
        firstName: userData.firstName,
        lastName: userData.lastName
      },
      // accountDetails: userData.accountDetails,
      address: {
        street: userData.street,
        houseNumber: parseInt(userData.houseNumber),
        postcode: parseInt(userData.postcode),
        location: userData.location
      },
      birthday: (this.datePipe.transform(userData.birthday, 'yyyy-MM-dd') as string),
      entryDate: (this.datePipe.transform(userData.entryDate, 'yyyy-MM-dd') as string),
      description: userData.description,
    }


    if (userData.cancellationDate) {
      userObject['cancellationDate'] = this.datePipe.transform(userData.cancellationDate, 'yyyy-MM-dd') as string
      leavingDate = this.calculateLeavingDate(userData.cancellationDate);
      userObject['leavingDate'] = this.datePipe.transform(leavingDate, 'yyyy-MM-dd') as string;
    }

    if (userData.familyId) {
      userObject = { ...userObject,
        familyId: {
        userId: parseInt(userData.familyId)
      }}
    }

    return userObject;
  }

  /**
   * Helper-Function to calculate the leaving Date of a Member.
   * If a User cancels his Membership after the 09-30, he will be a member for another year.
   * Memberships are always active until the last Day of a year (12-31)
   * Ex: cancel(2020-10-01) -> leaving (12-31-2021)
   *
   * @Author: Luca Ulrich
   * @param cancellationDate: Date - Date on which a User cancels his Membership
   * @private
   * @returns: date
   */
  private calculateLeavingDate(cancellationDate: string): Date {
    let leavingDate: Date;
    const cancellationDateObject = new Date(cancellationDate);
    const year = cancellationDateObject.getFullYear();
    const month = cancellationDateObject.getMonth();

    if (month > 8) {
      leavingDate = new Date(year + 1, 11, 31);
    } else {
      leavingDate = new Date(year, 11, 31);
    }
    return leavingDate;
  }

  /**
   * Function that gets triggered by a Change in users-form cancellation Date. It transforms the cancellationDate
   * to a string that gets displayed in the leavingDate field
   *
   * @Author: Luca Ulrich
   * @param event
   * @returns: void
   */
  onDateChange(event: MatDatepickerInputEvent<any>): void {
    const leavingDate = this.calculateLeavingDate(event.value as string)
    this.leavingDate = this.datePipe.transform(leavingDate, 'dd/MM/yyyy') as string;
  }
}
