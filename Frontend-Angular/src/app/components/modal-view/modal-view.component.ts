import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormService} from "../../services/form.service";

@Component({
  selector: 'app-modal-view',
  templateUrl: './modal-view.component.html',
  styleUrls: ['./modal-view.component.scss']
})
export class ModalViewComponent {
  constructor(private formService: FormService,
              private route: ActivatedRoute) {
    if('id' in route.snapshot.params) {
      formService.openModal(route.snapshot.params.id);
    } else {
      formService.openModal();
    }
  }
}
