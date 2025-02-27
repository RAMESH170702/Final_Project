import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from '@angular/router';
import { AuthserviceService } from './authservice.service';
import { inject } from '@angular/core';
import { RouteserviceService } from './routeservice.service';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot) => {
    const authService:AuthserviceService = inject(AuthserviceService);
    const routeService:RouteserviceService = inject(RouteserviceService);

    if(!authService.isLoggedIn()) {
      routeService.navigateToLoginView();
      return false;
    }
    else {
      return true
    }
};
