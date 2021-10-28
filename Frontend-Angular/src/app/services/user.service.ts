import { Injectable } from '@angular/core';
import { User } from "../model/user";
import { HttpClient } from "@angular/common/http";
import {Observable, of} from "rxjs";

// Dummy Data - needs to be deleted
const users: User[] = [
  {
    userId: 1,
    name: {
      firstName: "Jan",
      lastName: "Ramm"
    },
    address: {
      street: "Dorfstrasse",
      houseNumber: 1,
      postalCode: 25524,
      city: "Itzehoe"
    },
    birthday : new Date("2021-10-20"),
    entryDate : new Date("2021-10-28"),
    memberType : "Vollmember",
    accountDetails : 255255255255
  },
  {
    userId: 200,
    name: {
      firstName: "Luca",
      lastName: "Ulrich"
    },
    address: {
      street: "Postweg",
      houseNumber: 5,
      postalCode: 25524,
      city: "Itzehoe"
    },
    birthday : new Date("2000-01-01"),
    entryDate : new Date("2020-08-01"),
    cancellationDate: new Date("2020-10-10"),
    leavingDate: new Date("2021-12-31"),
    memberType : "Halbmember",
    accountDetails : 231123445,
    familyId: 1
  }
];

@Injectable({
  providedIn: 'root'
})

/**
 * Class to communicate with the API for Users/Members
 *
 * @Author: Luca Ulrich
 * @Version: 1.0
 */
export class UserService {

  constructor(private http: HttpClient) { }

  /**
   * Function to receive all Users/Members from the API
   *
   * @Author: Luca Ulrich
   * @Returns: Observable with an Array of Users
   */
  getUsers(): Observable<User[]> {
    return of(users);
    // return this.http.get<User[]>('/endpoint');
  }
}
