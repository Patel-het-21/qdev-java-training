// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
// import { Router, RouterModule } from '@angular/router';
// import { AuthService } from '../../../services/auth.service';
// import { ToastService } from '../../../shared/toast.service';

// @Component({
//   selector: 'app-register',
//   standalone: true,
//   imports: [CommonModule, ReactiveFormsModule, RouterModule],
//   templateUrl: './register.component.html',
//   styleUrls: ['./register.component.css']
// })
// export class RegisterComponent {

//   registerForm: FormGroup;

//   constructor(
//     private fb: FormBuilder,
//     private authService: AuthService,
//     private router: Router,
//     private toast: ToastService
//   ) {
//     this.registerForm = this.fb.group({
//       companyName: [''],
//       email: [''],
//       password: ['']
//     });
//   }

//   onSubmit() {
//     if (this.registerForm.invalid) {
//       return;
//     }

//     this.authService.register(this.registerForm.value).subscribe({
//       next: (response) => {
//         this.toast.show('Registration successful!', 'success');
//         this.router.navigate(['/employees']);
//       },
//       error: (error) => {
//         this.toast.show('Registration failed. Please try again.', 'error');
//       }
//     });
//   }

//   get f() {
//     return this.registerForm.controls;
//   }
// }
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { ToastService } from '../../../shared/toast.service';
import { map, catchError, of, timer, switchMap, Observable } from 'rxjs';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm: FormGroup;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toast: ToastService
  ) {
    // this.registerForm = this.fb.group({
    //   companyName: ['', [
    //     Validators.required,
    //     Validators.minLength(2),
    //     Validators.maxLength(255)
    //   ],],
    //   email: ['', [
    //     Validators.required,
    //     Validators.email,
    //     Validators.maxLength(254),
    //     Validators.pattern(/^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/)
    //   ]],
    //   password: ['', [
    //     Validators.required,
    //     Validators.minLength(6),
    //     Validators.maxLength(50),
    //     Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{6,}$/)
    //   ]]
    // });
    this.registerForm = this.fb.group({
      companyName: ['', {
        validators: [Validators.required, Validators.minLength(2), Validators.maxLength(100)],
        asyncValidators: [this.companyExistsValidator.bind(this)],
        updateOn: 'change'
      }],
      email: ['', {
        validators: [
          Validators.required,
          Validators.email,
          Validators.maxLength(254),
          Validators.pattern(/^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/)
        ],
        asyncValidators: [this.emailExistsValidator.bind(this)],
        updateOn: 'change'
      }],
      password: ['', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(50),
        Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{6,}$/)
      ]]
    });

  }

  companyExistsValidator(control: AbstractControl): Observable<ValidationErrors | null> {
    if (!control.value || control.value.length < 2) {
      return of(null);   // don't call API for short/empty input
    }

    return timer(400).pipe(   // 👈 debounce
      switchMap(() => this.authService.checkCompany(control.value)),
      map(exists => (exists ? { companyExists: true } : null)),
      catchError(() => of(null))
    );
  }

  emailExistsValidator(control: AbstractControl): Observable<ValidationErrors | null> {
    if (!control.value || control.invalid) {
      return of(null);
    }

    return timer(400).pipe(
      switchMap(() => this.authService.checkEmail(control.value)),
      map(exists => (exists ? { emailExists: true } : null)),
      catchError(() => of(null))
    );
  }
  onSubmit() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();   // 🔥 live errors
      return;
    }

    this.loading = true;

    this.authService.register(this.registerForm.value).subscribe({
      next: () => {
        this.toast.show('Registration successful!', 'success');
        this.router.navigate(['/employees']);
        this.loading = false;
      },
      error: () => {
        this.toast.show('Registration failed. Please try again.', 'error');
        this.loading = false;
      }
    });
  }

  get f() {
    return this.registerForm.controls;
  }
}
