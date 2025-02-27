import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../service/payment.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {
  orders:any[] = [];

  displayedColumns: string[] = ['orderId', 'paymentId', 'amount', 'method', 'status', 'movieId'];

  constructor(private paymentService: PaymentService) { }

  ngOnInit(): void {
    this.paymentService.getOrders().subscribe(
      (data) => {
        this.orders = data;
        console.log("Orders: " + this.orders)
      },
      (error) => {
        console.error("Error fetching orders")
      }
    )
  } 

}
