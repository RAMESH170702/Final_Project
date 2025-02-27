import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  // userEmail: string = '';
  // userName: string = '';
  // password: string = '';
  // confirmPassword: string = '';
  // phoneNumber: string = '';
  // submitted: boolean = false;
  // constructor(private http: HttpClient) { }

  // onSubmit() {
  //   this.submitted = true;
  //   if (!this.validateForm()) {
  //     return;
  //   }
  //     const user = {
  //       userEmail: this.userEmail,
  //       userName: this.userName,
  //       password: this.password,
  //       phoneNo: this.phoneNumber
  //     };

  //     this.http.post('http://localhost:9000/api/v2/registers', user)
  //       .subscribe(
  //         response => {
  //           console.log('Sign-up successful:', response);
  //         },
  //         error => {
  //           console.error('Sign-up failed:', error);
  //         }
  //       );
    
  // }
  // validateForm(): boolean {
  //   if (!this.userEmail || !this.userName || !this.password || !this.confirmPassword || !this.phoneNumber) {
  //     console.error('All fields are required');
  //     return false;
  //   }
  //   if (this.password !== this.confirmPassword) {
  //     console.error('Passwords do not match');
  //     return false;
  //   }

  //   const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  //   if (!emailPattern.test(this.userEmail)) {
  //     console.error('Invalid email format');
  //     return false;
  //   }

  //   const phonePattern = /^[789]\d{9}$/;
  //   if (!phonePattern.test(this.phoneNumber)) {
  //     console.error('Invalid phone number format');
  //     return false;
  //   }
  //   const userNamePattern = /^[a-zA-Z]{3,}$/;
  //   if (!userNamePattern.test(this.userName)) {
  //     console.error('Username must not contain numbers');
  //     return false;
  //   }

  //   if (this.userName.length < 3) {
  //     console.error('Username must contain at least 3 letters');
  //     return false;
  //   }
    
  //   return true;
  // }
  submitted = false;

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router:Router) { }

  signupForm = this.formBuilder.group({
    userEmail: ['', [Validators.required, Validators.email]],
    userName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z\s]{3,}$/), Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$'), Validators.minLength(8)]],
    confirmPassword: ['', Validators.required],
    phoneNumber: ['', [Validators.required, Validators.pattern(/^[789]\d{9}$/)]]
  }, {
    validators: this.passwordMatchValidator
  });
  onSubmit() {
    this.submitted = true;
    if (this.signupForm.invalid) {
      return;
    }

    const user = {
      userEmail: this.signupForm.value.userEmail,
      userName: this.signupForm.value.userName,
      password: this.signupForm.value.password,
      phoneNo: this.signupForm.value.phoneNumber
    };

    this.http.post('http://localhost:9000/api/v2/registers', user)
      .subscribe(
        response => {
          console.log('Sign-up successful:', response);
          alert('You have signed up successfully')
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Sign-up failed:', error);
          alert('Sign-up failed');
        }
      );
  }
  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');
    
    if (!password || !confirmPassword) {
      return null;
    }
  
    if (password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    } else {
      confirmPassword.setErrors(null);
      return null;
    }
  }
}
