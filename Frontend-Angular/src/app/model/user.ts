/**
 * Interface for a User-Model
 *
 * @author Luca Ulrich
 * @contributor Jan Ramm
 * @version 1.5
 */
export interface User {
  userId?: number;
  name: {
    firstName: string;
    lastName: string;
  };
  bankAccountDetails: string;
  address: {
    street: string;
    houseNumber: number;
    postcode: number;
    location: string;
  };
  birthday: string;
  entryDate: string;
  cancellationDate?: string;
  leavingDate?: string;

  description: string; // memberType
  amount?: number; // not needed but send anyway
  memberTypeChange: {
    description: string;
  };
  actualAmount?: number; // what the user needs to pay

  familyId?: {
    userId: number;
  };
}

/**
 * Interface for a formUser Model
 *
 * @author Luca Ulrich
 * @version 1.3
 */
export interface formUser {
  firstName: string;
  lastName: string;

  bankAccountDetails: string;

  street: string;
  houseNumber: string;
  postcode: string;
  location: string;

  birthday: string;
  entryDate: string;
  cancellationDate?: string;
  leavingDate?: string;

  description: string;
  descriptionChange?: string;
  actualAmount?: string;

  familyId?: string;
}
