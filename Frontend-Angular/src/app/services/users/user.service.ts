import { Injectable } from '@angular/core';
import {User} from '../../model/user';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

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

  /**
   * Function to receive all Users/Members from the API
   *
   * @Author: Luca Ulrich
   * @returns: Observable with an Array of Users
   */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/rest/user');
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`rest/user/${id}`);
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
    return this.http.post<User>('/rest/user', saveUser);
  }

  /**
   * Function to send a edited User to API
   *
   * @Author: Luca Ulrich
   * @param saveUser: User - a User to be edited
   * @returns: Observable with an User
   *
   */
  editUser(editUser: User): Observable<User>{
    return this.http.put<User>(`/rest/user/${editUser.userId}`, editUser);
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
    return this.http.delete<void>(`/rest/user/${userId}`)
  }
}
