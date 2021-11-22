/**
 * Interface for a Payment-Model
 *
 * @author Luca Ulrich
 * @version 1.1
 */
export interface Payment {
  invoiceNumber: number;
  amount: number;
  countStatus: boolean;
  year: number;
  bankAccountDetails: number;
  userId: {
    userId: number;
  };
}
