/**
 * Interface for a single User Model
 *
 * @Author: Jan Ramm & Luca Ulrich
 * @Version: 1.0
 */

export interface User {
  userId: number;
  name: {
    firstName: string;
    lastName: string;
  }
  address: {
    street: string;
    houseNumber: number;
    postalCode: number;
    city: string;
  }
  birthday: Date;
  entryDate: Date;
  cancellationDate?: Date;
  leavingDate?: Date;
  memberType: string;
  accountDetails: number;
  familyId?: number;
}
