import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
/**
 * Class to handle Notification Events
 *
 * @author: Jan Ramm & Luca Ulrich
 */
export class NotificationService {

  constructor(public snackBar: MatSnackBar) {
  }

  /** @type {MatSnackBarConfig} **/
  config: MatSnackBarConfig = {
    duration: 8000,
    horizontalPosition: 'right',
    verticalPosition: 'top'
  };

  /**
   * Function to open and configure success Notification-Event
   *
   * @author Luca Ulrich & Jan Ramm
   * @param {string} msg - Message to be displayed in Snackbar
   * @returns {void}
   */
  success(msg: string): void {
    this.config['panelClass'] = ['notification', 'success'];
    this.snackBar.open(msg, 'Schließen', this.config);
  }

  /**
   * Function to open and configure warning Notification-Event
   *
   * @author Luca Ulrich & Jan Ramm
   * @param {string} msg - Message to be displayed in Snackbar
   * @returns {void}
   */
  warn(msg: string): void {
    this.config['panelClass'] = ['notification', 'warn'];
    this.snackBar.open(msg, 'Schließen', this.config);
  }

  /**
   * Function to open and configure error Notification-Event
   *
   * @author Luca Ulrich & Jan Ramm
   * @param {string} msg - Message to be displayed in Snackbar
   * @returns {void}
   */
  error(msg: string): void {
    this.config['panelClass'] = ['notification', 'error'];
    this.snackBar.open(msg, 'Schließen', this.config);
  }

}
