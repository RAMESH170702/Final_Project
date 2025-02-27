import { Component } from '@angular/core';
import { AuthserviceService } from '../service/authservice.service';
import { Navigation, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  user:any = {};
  constructor(private authService: AuthserviceService, private router: Router) { }

  ngOnInit(): void {

  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  logout(): void {
    this.authService.logout();
  }

  getUserName(): string {
    this.user = this.authService.getUser();
    return this.user.userName;
  }
}
