import { Routes } from '@angular/router';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'add-employee', pathMatch: 'full' },
  { path: 'add-employee', component: EmployeeFormComponent },
  { path: 'employees', component: EmployeeListComponent }
];
