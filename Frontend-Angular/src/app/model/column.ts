import { Payment } from './payment';
import { User } from './user';

/**
 * Interface for Column Model
 * @author Luca Ulrich
 * @version 1.0
 */
export interface Column {
  columnDef: string;
  header: string;
  cell(arg0: User | Payment): string;
}
