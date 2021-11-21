import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../model/user';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root',
})

/**
 * Class to handle Form Operations
 *
 * @author Luca Ulrich
 * @contributor Jan Ramm
 */
export class FormService {
  getErrorMessage() {
    return 'Yeah!';
    //   if (this.form.get('firstName')?.hasError('required')) {
    //     return 'You must enter a value';
    //   }
    //   return this.form.hasError('pattern') ? 'Not a input format' : '';
  }
  /** @type {boolean} **/
  readonly: boolean = false;

  /**
   * @type {Subject<{id: number | undefined; action: string;}>}
   *    - number if id is present
   *    - undefined if id is missing
   *    - action - performed action event
   */
  private openModalSubject: Subject<{ id: number | undefined; action: string }> = new Subject<{
    id: number | undefined;
    action: string;
  }>();

  /**
   * @type {Observable<{ id: number | undefined; action: string;}>}
   *    - number if id is present
   *    - undefined if id is missing
   *    - action - performed action event
   */
  public modal: Observable<{ id: number | undefined; action: string }> = this.openModalSubject.asObservable();

  constructor(private datePipe: DatePipe) {}

  /** @type {FormGroup} **/
  form: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.pattern("^([ \u00c0-\u01ffa-zA-Z'\\-])+$")]),
    lastName: new FormControl('', [Validators.pattern("^([ \u00c0-\u01ffa-zA-Z'\\-])+$"), Validators.required]),
    // accountDetails: new FormControl('', [Validators.required,
    //   Validators.pattern("^[0-9]*$"), Validators.minLength(8)]),
    houseNumber: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.required]),
    postcode: new FormControl('', [
      Validators.pattern('^[0-9]*$'),
      Validators.required,
      Validators.maxLength(5),
      Validators.minLength(5),
    ]),
    street: new FormControl('', [Validators.pattern("^([ \u00c0-\u01ffa-zA-Z'\\-])+$"), Validators.required]),
    location: new FormControl('', [Validators.pattern("^([ \u00c0-\u01ffa-zA-Z'\\-])+$"), Validators.required]),
    birthday: new FormControl('', Validators.required),
    entryDate: new FormControl('', Validators.required),
    cancellationDate: new FormControl(''),
    leavingDate: new FormControl({ value: '', disabled: true }),
    description: new FormControl('', [Validators.required, Validators.pattern("^([ \u00c0-\u01ffa-zA-Z'\\-])+$")]),
    amount: new FormControl({ value: '', disabled: true }, [Validators.pattern('^[0-9]')]),
    familyId: new FormControl('', [Validators.pattern('^[0-9]*$')]),
  });

  /**
   * Function to initialize the Form. It fills the Form with given data. If no user is present, the form will be filled
   * with empty strings.
   *
   * @author Luca Ulrich
   * @param {User} user? - If user is given, Form will be filled with Userdata
   * @returns {void}
   */
  initializeFormGroup(user?: User): void {
    if (user) {
      this.form.setValue({
        firstName: user.name.firstName,
        lastName: user.name.lastName,
        // accountDetails: user.accountDetails,
        houseNumber: user.address.houseNumber,
        postcode: user.address.postcode,
        street: user.address.street,
        location: user.address.location,
        birthday: user.birthday,
        entryDate: user.entryDate,
        cancellationDate: user.cancellationDate ? user.cancellationDate : '',
        leavingDate: user.leavingDate ? (this.datePipe.transform(user.leavingDate, 'dd/MM/yyyy') as string) : '',
        description: user.description,
        amount: user.amount ? user.amount : '',
        familyId: user.familyId?.userId ? user.familyId.userId : '',
      });
    } else {
      this.form.setValue({
        firstName: '',
        lastName: '',
        // accountDetails: '',
        houseNumber: '',
        postcode: '',
        street: '',
        location: '',
        birthday: '',
        entryDate: '',
        cancellationDate: '',
        leavingDate: '',
        description: '',
        amount: '',
        familyId: '',
      });
    }
  }

  /**
   * Function to handle the accessibility of Input fields.
   *
   * @author Luca Ulrich
   * @param {boolean} status? - true if user can only view data (disabled access)
   *                         - false if user can edit data (enabled access)
   *                         - undefined: Button is clicked to switch modes (enable access)
   * @returns {void}
   */
  triggerAccessibility(status?: boolean): void {
    if (status) {
      this.readonly = status;
    } else {
      this.readonly = false;
    }
    if (this.readonly) {
      this.form.get('birthday')!.disable();
      this.form.get('description')!.disable();
      this.form.get('cancellationDate')!.disable();
      this.form.get('entryDate')!.disable();
    } else {
      this.form.get('birthday')!.enable();
      this.form.get('description')!.enable();
      this.form.get('cancellationDate')!.enable();
      this.form.get('entryDate')!.enable();
    }
  }

  /**
   * Function to reference the opened Modal.
   *
   * @author Luca Ulrich
   * @param {string} action - User Action performed in URL
   * @param {number} id? - ID of the User that the Action is performed on
   *                     - undefined if id is not present
   * @returns {void}
   */
  openModal(action: string, id?: number): void {
    const obj = { id: id, action: action };
    this.openModalSubject.next(obj);
  }
}
