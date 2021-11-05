import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})

/**
 * Class to handle Form Operations
 *
 * @Author: Luca Ulrich
 * @Version: 1.0
 */
export class FormService {
  readonly: boolean = false;
  private openModalSubject: Subject<{ id: number | undefined; action: string; }> = new Subject<{ id: number | undefined; action: string; }>();
  public modal: Observable<{ id: number | undefined; action: string; }> = this.openModalSubject.asObservable()

  constructor() { }

  form: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    accountDetails: new FormControl('', [Validators.required,
      Validators.pattern("^[0-9]*$"), Validators.minLength(8)]),
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

  /**
   * Function to initialize the Form
   *
   * @Author: Luca Ulrich
   * @param user? - If user is given, Form will be filled with Userdata
   * @returns: void
   */
  initializeFormGroup(user?: User): void {
    if (user) {
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
    } else {
      this.form.setValue({
        firstName: '',
        lastName: '',
        accountDetails: '',
        houseNumber: '',
        postalCode: '',
        street: '',
        city: '',
        birthday: '',
        entryDate: '',
        cancellationDate: '',
        leavingDate: '',
        memberType: '',
        familyId: ''
      });
    }
  }

  /**
   * Function to reference the opened Modal
   *
   * @Author: Luca Ulrich
   * @param action - User Action performed in URL
   * @param id - ID of the User that the Action is performed on
   * @returns: void
   */
  openModal(action: string, id?: number): void {
    const obj = {"id": id,"action": action}
    this.openModalSubject.next(obj);
  }
}
