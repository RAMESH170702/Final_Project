import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  initiatePayment(movieId: number, amount: string): Observable<any> {
    const token = localStorage.getItem('token')
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const requestBody = {
      movieId: movieId,
      amount: amount
    };
    return this.http.post('http://localhost:9000/api/v2/user/order', requestBody , { headers, responseType: 'text' });
  }

  addOrder(paymentId:any, orderId:any, movieId:any): Observable<any> {
    const token = localStorage.getItem('token')
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    const requestBody = {
      movieId: movieId,
      orderId: orderId,
      paymentId: paymentId
    }

    return this.http.post('http://localhost:9000/api/v2/user/payment', requestBody , { headers });
  }

  getOrders(): Observable<any[]> {
    const token = localStorage.getItem('token')
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>('http://localhost:9000/api/v2/user/orders', { headers })
  }
}
