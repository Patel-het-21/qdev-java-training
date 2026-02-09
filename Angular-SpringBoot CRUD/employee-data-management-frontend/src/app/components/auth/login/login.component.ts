// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
// import { Router, RouterModule } from '@angular/router';
// import { AuthService } from '../../../services/auth.service';
// import { ToastService } from '../../../shared/toast.service';

// @Component({
//   selector: 'app-login',
//   standalone: true,
//   imports: [CommonModule, ReactiveFormsModule, RouterModule],
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent {

//   loginForm: FormGroup;

//   constructor(
//     private fb: FormBuilder,
//     private authService: AuthService,
//     private router: Router,
//     private toast: ToastService
//   ) {
//     this.loginForm = this.fb.group({
//       email: [''],
//       password: ['']
//     });
//   }

//   onSubmit() {
//     if (this.loginForm.invalid) {
//       return;
//     }

//     this.authService.login(this.loginForm.value).subscribe({
//       next: (response) => {
//         this.toast.show('Login successful!', 'success');
//         this.router.navigate(['/employees']);
//       },
//       error: (error) => {
//         this.toast.show('Invalid email or password', 'error');
//       }
//     });
//   }

//   get f() {
//     return this.loginForm.controls;
//   }
// }
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { ToastService } from '../../../shared/toast.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm: FormGroup;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toast: ToastService
  ) {
    this.loginForm = this.fb.group({
      email: ['', [
        Validators.required,
        Validators.email,
        Validators.maxLength(254),
        Validators.pattern(/^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/)
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(50)
      ]]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.loading = true;

    this.authService.login(this.loginForm.value).subscribe({
      next: () => {
        this.toast.show('Login successful!', 'success');
        this.router.navigate(['/employees']);
        this.loading = false;
      },
      error: () => {
        this.toast.show('Invalid email or password', 'error');
        this.loading = false;
      }
    });
  }

  get f() {
    return this.loginForm.controls;
  }
}
