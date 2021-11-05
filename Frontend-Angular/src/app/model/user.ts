/**
 * Interface for a User-Model
 *
 * @Author: Luca Ulrich
 * @Version: 1.1
 */
export interface User {
  userId?: number
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
  birthday: string;
  entryDate: string;
  cancellationDate?: string;
  leavingDate?: string;
  memberType: string;
  accountDetails: number;
  familyId?: number;
}

/**
 * Interface for a formUser Model
 *
 * @Author: Luca Ulrich
 * @Version: 1.0
 */
export interface formUser {
  accountDetails: number;
  birthday: Date;
  cancellationDate?: Date;
  city: string,
  entryDate: Date;
  familyId?: string;
  firstName: string;
  houseNumber: string;
  lastName: string;
  leavingDate: Date;
  memberType: string;
  postalCode: string;
  street: string;
}
