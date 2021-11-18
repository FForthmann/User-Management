import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Payment} from "../../model/payment";
import {catchError, retry} from "rxjs/operators";

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
    return this.http.get<Payment[]>('/rest/payments').pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to recieve a single Payment with given ID
   *
   * @Author: Luca Ulrich
   * @param id: number - ID to receive Payment from
   * @returns: Observable with an Payment-Object
   */
  getPayment(id: number): Observable<Payment> {
    return this.http.get<Payment>(`/rest/payments/${id}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
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
    return this.http.put<Payment>(`/rest/payments/${payment.invoiceNumber}`, payment).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to handle Error-Events in Payment-Service. It throws the errorMessage back to the Component.
   *
   * @Author: Luca Ulrich
   * @param error: HttpErrorResponse - Errorevent passed to Function
   * @returns: Observable<never>
   */
  handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage: string = '';

    switch (error.status) {
      case 504:
        errorMessage = 'Keine Verbindung zur Datenbank!';
        break;
      case 400:
        errorMessage = error.error.localizedMessage;
        break;
      default:
        errorMessage = 'Es ist ein unbekannter Fehler aufgetreten!';
        break;
    }
    return throwError(errorMessage);
  }
}
