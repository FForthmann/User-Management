import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';
import {Observable, of, Subject} from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';

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
  private openModalSubject: Subject<number | undefined> = new Subject<number | undefined>();
  public modal = this.openModalSubject.asObservable();

  constructor(private http: HttpClient) {}

  form: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    accountDetails: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$"), Validators.minLength(8)]),
    street: new FormControl('', Validators.required),
    houseNumber: new FormControl('', [Validators.pattern("^[0-9]*$"), Validators.required]),
    postalCode: new FormControl('', [Validators.pattern("^[0-9]*$"), Validators.required]),
    city: new FormControl('', Validators.required),
    birthday: new FormControl('', Validators.required),
    entryDate: new FormControl('', Validators.required),
    cancellationDate: new FormControl(''),
    leavingDate: new FormControl(''),
    memberType: new FormControl('', Validators.required),
    familyId: new FormControl('',[Validators.pattern("^[0-9]*$")])
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

  openModal(id?: number): void {
    this.openModalSubject.next(id);
  }

  /**
   * Function to receive all Users/Members from the API
   *
   * @Author: Luca Ulrich
   * @returns: Observable with an Array of Users
   */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/rest/user');
  }

  /**
   * Function to send a saved User to API
   *
   * @Author: Luca Ulrich
   * @param saveUser - a User to be saved
   * @returns: Observable with an Array of Users
   *
   * @TODO: Fix Any Variable
   */
  saveUser(saveUser: User): Observable<any> {
    return this.http.post('/rest/user', saveUser);
  }


  editUser(editUser: User): any{
    return [];
    //return this.http.put(`/update-user/${id}`, editUser);
 }
}
