import { Injectable } from '@angular/core';
import {User} from '../../model/user';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from "rxjs/operators";

@Injectable({
  providedIn: 'root',
})

/**
 * Class to communicate with the API for Users/Members
 *
 * @Author: Luca Ulrich
 * @Version: 1.2
 */
export class UserService {

  constructor(private http: HttpClient) {}

  /**
   * Function to receive all Users/Members from the API
   *
   * @Author: Luca Ulrich
   * @returns: Observable with an Array of Users
   */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/rest/user').pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to get a single User from the API
   *
   * @Author: Luca Ulrich
   * @param id: number - UserID to get the User
   * @returns: Observable with a Single User Object
   */
  getUser(id: number): Observable<User> {
    return this.http.get<User>(`rest/user/${id}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to send a saved User to API
   *
   * @Author: Luca Ulrich
   * @param saveUser: User - a User to be saved
   * @returns: Observable with an User
   *
   */
  saveUser(saveUser: User): Observable<User> {
    return this.http.post<User>('/rest/user', saveUser).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to send a edited User to API
   *
   * @Author: Luca Ulrich
   * @param editUser: User - a User to be edited
   * @returns: Observable with an User
   *
   */
  editUser(editUser: User): Observable<User>{
    return this.http.put<User>(`/rest/user/${editUser.userId}`, editUser).pipe(
      retry(1),
      catchError(this.handleError)
    );
 }

  /**
   * Function to send a delete Request to API
   *
   * @Author: Luca Ulrich
   * @param userId: number - a UserId to be deleted
   * @returns: void
   *
   */
  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`/rest/user/${userId}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Function to handle Error-Events in Service. It throws the errorMessage back to the Component.
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
