import { Injectable } from '@angular/core';
import {User} from '../model/user';
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
   * @param saveUser - a User to be saved
   * @returns: Observable with an Array of Users
   *
   * @TODO: Fix Any Variable
   */
  saveUser(saveUser: User): Observable<any> {
    return this.http.post('/rest/user', saveUser);
  }


  editUser(editUser: User): Observable<any>{
    return this.http.put(`/rest/user/${editUser.userId}`, editUser);
 }
}
