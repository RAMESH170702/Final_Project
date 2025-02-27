import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteserviceService {

  constructor(private router: Router) { }
  navigateToLoginView() {
    this.router.navigate(["login"]);
  }

  navigateToHomeView() {
    this.router.navigate(["/"]);
  }
}
