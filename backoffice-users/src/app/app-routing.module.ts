import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './entities/user/user-list/user-list.component';
import { HomeComponent } from './home/home.component';
import { UserFormComponent } from './entities/user/user-form/user-form.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'users', component: UserListComponent },
  { path: 'users/:userId', component: UserFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
