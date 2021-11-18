/**
 * Interface for a Payment-Model
 *
 * @Author: Luca Ulrich
 * @Version: 1.1
 */
export interface Payment {
  invoiceNumber: number;
  amount: number,
  countStatus: boolean,
  year: number;
  bankAccountDetails: number;
  userId: {
    userId: number;
  };
}
