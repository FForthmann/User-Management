import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Payment } from '../../model/payment';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
/**
 * Class to communicate with the API for Payments
 *
 * @author Luca Ulrich
 * @version 1.2
 */
export class PaymentService {

  constructor(private http: HttpClient) {
  }

  /**
   * Function to send a get Request to API.
   * It receives all Payments from the API.
   *
   * @author Luca Ulrich
   * @returns {Observable<Payment[]>} - Observable with an Array of Payments
   */
  getPayments(): Observable<Payment[]> {
    return this.http.get<Payment[]>('/rest/payments').pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to send a get Request to API.
   * It receives a single Payment with given ID.
   *
   * @author Luca Ulrich
   * @param  {number} id - ID to receive Payment from
   * @returns {Observable<Payment[]>} - Observable with a single Object of Payments
   */
  getPayment(id: number): Observable<Payment> {
    return this.http.get<Payment>(`/rest/payments/${id}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to send a put Request to API.
   * It updates a saved Payment in the Database.
   *
   * @author Luca Ulrich
   * @param {Payment} payment - Payment to be updated
   * @returns {Observable<Payment[]>} - Observable with a single Object of Payments
   *
   */
  editPayment(payment: Payment): Observable<Payment> {
    return this.http.put<Payment>(`/rest/payments/${payment.invoiceNumber}`, payment).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to handle Error-Events in Service. It throws the errorMessage back to the Component.
   *
   * @author Luca Ulrich
   * @param {HttpErrorResponse} error - Errorevent passed to Function
   * @returns {Observable<never>}
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
