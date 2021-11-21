import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
/**
 * Service to handle Errorevents in other Services
 *
 * @author Luca Ulrich
 * @version 1.0
 */
export class ErrorService {
  constructor() {}

  /**
   * Function to handle Error-Events in Services. It throws the errorMessage back to the Component, where the
   * Service was used.
   *
   * @author Luca Ulrich
   * @param {HttpErrorResponse} error - Errorevent passed to Function
   * @returns {Observable<never>}
   */
  handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage: string = 'Es ist ein unbekannter Fehler aufgetreten!';

    switch (error.status) {
      case 504:
        errorMessage = 'Keine Verbindung zur Datenbank!';
        break;
      case 400:
        if (error.error.message) {
          errorMessage = error.error.message;
        }
        break;
      default:
        errorMessage = 'Es ist ein unbekannter Fehler aufgetreten!';
        break;
    }
    return throwError(errorMessage);
  }
}
