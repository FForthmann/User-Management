import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Payment} from "../../model/payment";

@Injectable({
  providedIn: 'root'
})
/**
 * Class to communicate with the API for Payments
 *
 * @Author: Luca Ulrich
 */
export class PaymentService {

  constructor(private http: HttpClient) { }

  /**
   * Function to receive all Payments from the API
   *
   * @Author: Luca Ulrich
   * @returns: Observable with an Array of Payments
   */
  getPayments(): Observable<Payment[]> {
    return this.http.get<Payment[]>('/rest/account');
  }

  /**
   * Function to recieve a single Payment with given ID
   *
   * @Author: Luca Ulrich
   * @param id: number - ID to recieve Payment from
   * @returns: Observable with an Payment-Object
   */
  getPayment(id: number): Observable<Payment> {
    return this.http.get<Payment>(`/rest/account/${id}`);
  }

  /**
   * Function to send a edited Payment to API
   *
   * @Author: Luca Ulrich
   * @param payment: Payment - Payment to be updated
   * @returns: Observable with an Payment
   *
   */
  editPayment(payment: Payment): Observable<Payment>{
    return this.http.put<Payment>(`/rest/account/${payment.invoiceNumber}`, payment);
  }
}
