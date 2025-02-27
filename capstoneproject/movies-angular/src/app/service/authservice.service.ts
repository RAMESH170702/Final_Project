import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RouteserviceService } from './routeservice.service';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {

  user:any = {}
  
  constructor(private http: HttpClient,
              private routerService: RouteserviceService) { }
  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
  getUsername(userEmail: string): Observable<string> {
    return this.http.get<string>(`http://localhost:9000/api/v2/user/${userEmail}`);
  }

  logout(): void {
    localStorage.removeItem('token');
    this.routerService.navigateToHomeView();
  }

  setUser(user: any) {
    this.user = user;
  }

  getUser(): any {
    return this.user;
  }
}
