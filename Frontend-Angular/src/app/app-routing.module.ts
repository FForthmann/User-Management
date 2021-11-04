import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UsersComponent} from "./components/users.component";
import {ModalViewComponent} from "./components/modal-view/modal-view.component";

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    redirectTo: "users"
  },
  {
    path: "users",
    component: UsersComponent,
    children: [
      {
        path: "create",
        component: ModalViewComponent
      },
      {
        path: "edit/:id",
        component: ModalViewComponent
      },
    ]
  },
  { path: '**', redirectTo: 'users' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
