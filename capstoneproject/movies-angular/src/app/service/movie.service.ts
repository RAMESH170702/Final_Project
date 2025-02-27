import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { Movie } from '../model/Movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  // private baseUrl = 'http://localhost:9000/api/v3/favorites/favorite/user/userEmail';

  constructor(private http: HttpClient) { }

  // authAppBaseUrl = "http://localhost:9000";

  // registerUser(signUpUserAuthData:any){
  //   return this.http.post(this.authAppBaseUrl+"/api/v1/save",signUpUserAuthData);
  // }
  // addUser(signUpUserProductData:any) {
  //   return this.http.post(this.authAppBaseUrl+"/api/v2/registers",signUpUserProductData);
  // }

  // getUserDetails(){
  //   let httpHeaders=new HttpHeaders({
  //     "authorization":'Bearer ' + localStorage.getItem("token")
  //   });
  //   let requestOptions={headers:httpHeaders};
  //   this.http.get(this.authAppBaseUrl+"/api/v2/getUserDetails",requestOptions);

  // }
  // getFavoriteMovies(): Observable<Movie[]> {
  //   return this.http.get<Movie[]>(`${this.baseUrl}`);
  // }

  // getAllMovies(): Observable<Movie[]> {
  //   return this.http.get<Movie[]>(`${this.baseUrl}/all`);
  // }
  addToFavorites(userEmail: any, movieId: any): Observable<any> {
    // const apiUrl = `http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}/movie/${movieId}`;

    return this.http.post(`http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}/movie/${movieId}`, {userEmail, movieId});
  }
  removeFromFavorites(userEmail: any, movieId: any): Observable<any> {
    return this.http.delete(`http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}/movie/${movieId}`);
  }
  getAllFavorites(userEmail: any): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}`);
  }
  getFavoriteStatus(userEmail: any, movieId: any): Observable<any> {
    return this.http.get<any>(`http://localhost:9000/api/v3/favorites/favorite/user/${userEmail}/movie/${movieId}`);
  }
  toggleFavorite(userEmail: any, movieId: string): Observable<any> {

    return this.getFavoriteStatus(userEmail, movieId).pipe(
      switchMap(isFavorite => {
        if (isFavorite) {
          return this.removeFromFavorites(userEmail, movieId);
        } else {
          return this.addToFavorites(userEmail, movieId);
        }
      })
    );
  }

  getMovieOwnedStatus(movieId:any): Observable<any> {
    const token = localStorage.getItem('token')
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<boolean>(`http://localhost:9000/api/v2/user/owned/${movieId}`, {headers});
  }

}
