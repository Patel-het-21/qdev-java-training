// import { Routes } from '@angular/router';
// import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
// import { EmployeeListComponent } from './components/employee-list/employee-list.component';

// export const routes: Routes = [
//   { path: '', redirectTo: 'add-employee', pathMatch: 'full' },
//   { path: 'add-employee', component: EmployeeFormComponent },
//   { path: 'employees', component: EmployeeListComponent }
// ];
import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  // Public routes
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // Protected routes (require authentication)
  { 
    path: 'employees', 
    component: EmployeeListComponent,
    canActivate: [authGuard]
  },
  { 
    path: 'add-employee', 
    component: EmployeeFormComponent,
    canActivate: [authGuard]
  },

  // Default redirect
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  
  // Wildcard route
  { path: '**', redirectTo: '/login' }
];