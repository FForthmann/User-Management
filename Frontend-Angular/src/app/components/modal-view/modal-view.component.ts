import { Component, OnInit } from '@angular/core';
import {MatDialogConfig} from "@angular/material/dialog";
import {UserFormComponent} from "../user-form/user-form.component";
import {UserService} from "../../services/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-modal-view',
  templateUrl: './modal-view.component.html',
  styleUrls: ['./modal-view.component.scss']
})
export class ModalViewComponent {
  constructor(private userService: UserService,
              private route: ActivatedRoute) {
    if('id' in route.snapshot.params) {
      userService.openModal(route.snapshot.params.id);
    } else {
      userService.openModal();
    }
  }
}
