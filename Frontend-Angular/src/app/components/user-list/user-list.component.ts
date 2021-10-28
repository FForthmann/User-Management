import { Component, OnInit } from '@angular/core';
import { User } from "../../model/user";

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

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[] = ['Mitgliedsnummer','Name','Mitgliedsart','Aktionen'];
  dataSource = users;

  constructor() { }

  ngOnInit(): void {
  }

}
