import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormService } from '../../services/form/form.service';

@Component({
  selector: 'app-modal-view',
  templateUrl: './modal-view.component.html',
  styleUrls: ['./modal-view.component.scss'],
})
export class ModalViewComponent {
  constructor(private formService: FormService, private route: ActivatedRoute) {
    // Checks the active url for active Route
    switch (route.snapshot.url[0].path) {
      case 'create':
        formService.openModal('create');
        break;
      case 'edit':
        formService.openModal('edit', route.snapshot.params.id);
        break;
      case 'delete':
        formService.openModal('delete', route.snapshot.params.id);
        break;
    }
  }
}
