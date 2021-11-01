import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';

// Dummy Data - needs to be deleted
const users: User[] = [
  {
    userId: 1,
    name: {
      firstName: 'Jan',
      lastName: 'Ramm',
    },
    address: {
      street: 'Dorfstrasse',
      houseNumber: 1,
      postalCode: 25524,
      city: 'Itzehoe',
    },
    birthday: new Date('2021-10-20'),
    entryDate: new Date('2021-10-28'),
    memberType: 'Vollmember',
    accountDetails: 255255255255,
  },
  {
    userId: 200,
    name: {
      firstName: 'Luca',
      lastName: 'Ulrich',
    },
    address: {
      street: 'Postweg',
      houseNumber: 5,
      postalCode: 25524,
      city: 'Itzehoe',
    },
    birthday: new Date('2000-01-01'),
    entryDate: new Date('2020-08-01'),
    cancellationDate: new Date('2020-10-10'),
    leavingDate: new Date('2021-12-31'),
    memberType: 'Halbmember',
    accountDetails: 231123445,
    familyId: 1,
  },
];

@Injectable({
  providedIn: 'root',
})

/**
 * Class to communicate with the API for Users/Members
 *
 * @Author: Luca Ulrich
 * @Version: 1.0
 */
export class UserService {
  constructor(private http: HttpClient) {}

  form: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    accountDetails: new FormControl('', Validators.required),
    street: new FormControl('', Validators.required),
    houseNumber: new FormControl('', Validators.required),
    postalCode: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    birthday: new FormControl('', Validators.required),
    entryDate: new FormControl('', Validators.required),
    cancellationDate: new FormControl(''),
    leavingDate: new FormControl(''),
    memberType: new FormControl('1', Validators.required),
    familyId: new FormControl('')
  });

  initializeFormGroup(user?: User) {
    this.form.setValue({
      firstName: '',
      lastName: '',
      accountDetails: '',
      houseNumber: '',
      postalCode: '',
      street:'',
      city: '',
      birthday: '',
      entryDate: '',
      cancellationDate: '',
      leavingDate: '',
      memberType: '',
      familyId: ''
    });
  }

  populateForm(user: User) {
    this.form.setValue({
      firstName: user.name.firstName,
      lastName: user.name.lastName,
      accountDetails: user.accountDetails,
      houseNumber: user.address.houseNumber,
      postalCode: user.address.postalCode,
      street: user.address.street,
      city: user.address.city,
      birthday: user.birthday,
      entryDate: user.entryDate,
      cancellationDate: user.cancellationDate?user.cancellationDate:'',
      leavingDate: user.leavingDate? user.leavingDate:'',
      memberType: user.memberType,
      familyId: user.familyId? user.familyId:''
    });
  }

  /**
   * Function to receive all Users/Members from the API
   *
   * @Author: Luca Ulrich
   * @returns: Observable with an Array of Users
   */
  getUsers(): Observable<User[]> {
    return of([...users]);
    // return this.http.get<User[]>('/endpoint');
  }

  /**
   * Function to send a saved User to API
   *
   * @Author: Luca Ulrich
   * @param savedUser - a User to be saved
   * @returns: Observable with an Array of Users
   */
  saveUser(savedUser: User): Observable<User[]> {
    users.push(savedUser);
    return of(users);
    // return this.http.post('/endpoint', savedUser);
  }


  editUser(editUser: User): Observable<User[]> {
    return of(users)
    //return this.http.put(`/update-user/${id}`, editUser);
  }
}
