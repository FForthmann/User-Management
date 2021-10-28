import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  @Output() save = new EventEmitter<User>();

  constructor(public userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.save.emit(this.buildUser(this.userService.form.value))
  }

  /**
   * Function to build a User Object to pass to other functions
   *
   * @Author: Luca Ulrich
   * @param userData
   * @returns: User Object
   * @TODO: Build a formUser Model or refactor this
   */
  buildUser(userData: any): User {
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
