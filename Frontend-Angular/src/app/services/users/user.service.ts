import { Injectable } from '@angular/core';
import { User } from '../../model/user';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { ErrorService } from '../error/error.service';

@Injectable({
  providedIn: 'root',
})

/**
 * Class to communicate with the API for Users/Members
 *
 * @author Luca Ulrich
 * @version 1.2
 */
export class UserService {
  constructor(private http: HttpClient, private errorHandler: ErrorService) {}

  /**
   * Function to send a get Request to API.
   * It receives all Users/Members from the API
   *
   * @author Luca Ulrich
   * @returns {Observable<User[]>} - Observable with an Array of Users
   */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/rest/user').pipe(retry(1), catchError(this.errorHandler.handleError));
  }

  /**
   * Function to send a get Request to API.
   * It gets a single User from the API
   *
   * @author Luca Ulrich
   * @param {number} id - UserID to get the User from the API
   * @returns {Observable<User>} - Observable with a Single User Object
   */
  getUser(id: number): Observable<User> {
    return this.http.get<User>(`rest/user/${id}`).pipe(retry(1), catchError(this.errorHandler.handleError));
  }

  /**
   * Function to send a post Request to API.
   * It saves a User in the Database.
   *
   * @author Luca Ulrich
   * @param {User} saveUser - a User to be saved to DB / API
   * @returns {Observable<User>} - Observable with a Single User Object
   *
   */
  saveUser(saveUser: User): Observable<User> {
    return this.http.post<User>('/rest/user', saveUser).pipe(retry(1), catchError(this.errorHandler.handleError));
  }

  /**
   * Function to send a update Request to API
   * It updates a saved User with new given Data.
   *
   * @author Luca Ulrich
   * @param {User} editUser - a User to be edited
   * @returns {Observable<User>} - Observable with a Single User Object
   *
   */
  editUser(editUser: User): Observable<User> {
    console.log(editUser);
    return this.http
      .put<User>(`/rest/user/${editUser.userId}`, editUser)
      .pipe(retry(1), catchError(this.errorHandler.handleError));
  }

  /**
   * Function to send a delete Request to API.
   * It deletes the User from the Database.
   *
   * @author Luca Ulrich
   * @param {number} userId - The UserID to be deleted.
   * @returns: void
   *
   */
  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`/rest/user/${userId}`).pipe(retry(1), catchError(this.errorHandler.handleError));
  }
}
