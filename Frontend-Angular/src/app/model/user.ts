/**
 * Interface for a User-Model
 *
 * @author Luca Ulrich
 * @contributor Jan Ramm
 * @version 1.2
 */
export interface User {
  userId?: number
  name: {
    firstName: string;
    lastName: string;
  }
  // accountDetails: number
  address: {
    street: string;
    houseNumber: number;
    postcode: number;
    location: string;
  }
  birthday: string;
  entryDate: string;
  cancellationDate?: string;
  leavingDate?: string;
  description: string;
  amount?: number;
  familyId?: {
    userId: number;
  };
}

/**
 * Interface for a formUser Model
 *
 * @author Luca Ulrich
 * @version 1.1
 */
export interface formUser {
  firstName: string;
  lastName: string;

  // accountDetails: string;

  street: string;
  houseNumber: string;
  postcode: string;
  location: string,

  birthday: string;
  entryDate: string;
  cancellationDate?: string;
  leavingDate?: string;

  description: string;
  amount?: string;

  familyId?: string;
}
