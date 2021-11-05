/**
 * Interface for a Payment-Model
 *
 * @Author: Luca Ulrich
 * @Version: 1.0
 */
export interface Payment {
  "invoiceNumber": number;
  "amount": number,
  "countStatus": number, //will be changed to Boolean in a new Version
  "year": number,
  "accountDetails"?: number // not present yet
  "userId": { // may be changed to userId without nesting
    "userId": number
  }
}
