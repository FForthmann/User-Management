import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './components/users/users.component';
import { ModalViewComponent } from './components/modal-view/modal-view.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { PrintViewComponent } from './components/print-view/print-view.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'users',
  },
  {
    path: 'users',
    component: UsersComponent,
    children: [
      {
        path: 'create',
        component: ModalViewComponent,
      },
      {
        path: 'edit/:id',
        component: ModalViewComponent,
      },
      {
        path: 'delete/:id',
        component: ModalViewComponent,
      },
      {
        path: 'print',
        component: PrintViewComponent,
      },
      {
        path: 'print/:year',
        component: PrintViewComponent,
      },
    ],
  },
  {
    path: 'payments',
    component: PaymentsComponent,
    children: [
      {
        path: 'print',
        component: PrintViewComponent,
      },
      {
        path: 'print/:year',
        component: PrintViewComponent,
      },
    ],
  },
  { path: '**', redirectTo: 'users' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
