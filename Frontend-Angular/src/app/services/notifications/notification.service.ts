import { Injectable } from '@angular/core';
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
/**
 * Class to handle Notification Events
 *
 * @Author: Jan Ramm & Luca Ulrich
 */
export class NotificationService {

  constructor(public snackBar: MatSnackBar) { }

  config: MatSnackBarConfig = {
    duration: 8000,
    horizontalPosition: 'right',
    verticalPosition: 'top',
  }

  /**
   * Function to open and configure Notification-Event
   *
   * @Author: Luca Ulrich & Jan Ramm
   * @param msg: string - Message to be displayed
   * @returns: void
   */
  success(msg: string): void {
    this.config['panelClass'] = ['notification', 'success'];
    this.snackBar.open(msg,'Schließen', this.config);
  }

  /**
   * Function to open and configure Notification-Event
   *
   * @Author: Luca Ulrich & Jan Ramm
   * @param msg: string - Message to be displayed
   * @returns: void
   */
  warn(msg: string): void {
    this.config['panelClass'] = ['notification', 'warn'];
    this.snackBar.open(msg, 'Schließen', this.config);
  }

  /**
   * Function to open and configure Notification-Event
   *
   * @Author: Luca Ulrich & Jan Ramm
   * @param msg: string - Message to be displayed
   * @returns: void
   */
  error(msg: string): void {
    this.config['panelClass'] = ['notification', 'error'];
    this.snackBar.open(msg, 'Schließen', this.config);
  }

}
