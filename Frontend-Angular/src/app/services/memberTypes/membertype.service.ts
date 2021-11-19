import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MemberType } from '../../model/memberType';

@Injectable({
  providedIn: 'root'
})

/**
 * Class to communicate with the API for MemberTypes
 *
 * @author Luca Ulrich & Jan Ramm
 */
export class MembertypeService {

  constructor(private http: HttpClient) {
  }

  /**
   * Function to send a get Request to API.
   * It receives all Membertypes.
   *
   * @author Luca Ulrich & Jan Ramm
   * @returns {Observable<MemberType[]>} - Observable<Array> with all MemberType Objects
   */
  getMemberTypes(): Observable<MemberType[]> {
    return this.http.get<MemberType[]>(`/rest/memberType`);
  }

  /**
   * Function to send get Request to API.
   * It receives a specific Membertype with given ID.
   *
   * @Author: Luca Ulrich & Jan Ramm
   * @param {number} id - ID to receive MemberType Object
   * @returns {Observable<MemberType>} - Observable with a single MemberType Object
   */
  getMemberType(id: number): Observable<MemberType> {
    return this.http.get<MemberType>(`/rest/memberType/${id}`);
  }
}
