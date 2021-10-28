import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../../model/user";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  @Input() user!: User;
  @Output() save = new EventEmitter<User>();

  constructor(public userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.save.emit(this.user);
  }

}
