import { Component, OnInit } from '@angular/core';
import { User } from "../../model/user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})

export class UserComponent implements OnInit {
  user: User = {
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
  };

  constructor() { }

  ngOnInit(): void {}

}
