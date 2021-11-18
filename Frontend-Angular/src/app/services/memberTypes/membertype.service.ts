import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MemberType} from "../../model/memberType";

@Injectable({
  providedIn: 'root'
})

/**
 * Class to communicate with the API for MemberTypes
 *
 * @Author: Luca Ulrich & Jan Ramm
 */
export class MembertypeService {

  constructor(private http: HttpClient) {}

  /**
   * Function to receive all Membertypes
   *
   * @Author: Luca Ulrich & Jan Ramm
   * @returns: Observable<MemberType[]> - Array with all MemberType Objects
   */
  getMemberTypes(): Observable<MemberType[]> {
    return this.http.get<MemberType[]>(`/rest/memberType`);
  }

  /**
   * Function to receive a specific Membertype with given ID
   *
   * @Author: Luca Ulrich & Jan Ramm
   * @param id: number - ID to receive
   * @returns: Observable<MemberType> - Observable with a single MemberType Object
   */
  getMemberType(id: number): Observable<MemberType> {
    return this.http.get<MemberType>(`/rest/memberType/${id}`);
  }
}
