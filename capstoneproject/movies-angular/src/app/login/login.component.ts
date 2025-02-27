import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthserviceService } from '../service/authservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userEmail: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router,
              private authService:AuthserviceService) { }

  onSubmit() {
    this.http.post<any>('http://localhost:9000/api/v1/login', { userEmail: this.userEmail, password: this.password })
      .subscribe(
        response => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('userEmail', this.userEmail);
          const token = localStorage.getItem('token')
          const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
          this.http.get('http://localhost:9000/api/v2/user', { headers }).subscribe(
            response => {
              this.authService.setUser(response);
              console.log("User is " + response)
            },
            error => {
              console.log("Error getting user details");
            }
          );
          this.router.navigate([''], { state: { userEmail: this.userEmail } });
        },
        error => {
          console.error('Login failed:', error);
          alert('Email and Password do not match');
        }
      );
  }
}
