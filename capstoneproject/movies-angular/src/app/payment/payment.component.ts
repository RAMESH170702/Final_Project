import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PaymentService } from '../service/payment.service';
import { AuthserviceService } from '../service/authservice.service';

declare var Razorpay: any;

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent {

  @Input() movieId:any;
  @Output() moviePurchased: EventEmitter<boolean> = new EventEmitter<boolean>();
  amount:string = "100";

  constructor(private paymentService: PaymentService, private authService:AuthserviceService) { }
  ngOnInit(): void {
  } 
  initiatePayment(): void {
    const amount = 1000; // Example amount
    const currency = 'INR'; // Example currency
    const receiptId = '12345'; // Example receipt ID
    this.paymentService.initiatePayment(this.movieId, this.amount).subscribe(
      (data) => {
        console.log('Payment order created:', data);
        // Handle payment initiation success
        this.pay(data)
      },
      (error) => {
        console.error('Error creating payment order:', error);
        // Handle payment initiation error
      }
    );
  }

  pay(orderId:string) :void {
    let user = this.authService.getUser();
    const Options = {
      description: 'Ordering Movie ' + this.movieId,
      currency: 'INR',
      amount: this.amount,
      order_id:orderId,
      name: user.userName,
      key: 'rzp_test_sT0jZz5QQMJ6ME',
      image: 'src/assests/logo.jpg',
      prefill: {
        name: user.userName,
        email: user.userEmail,
        phone: user.phoneNo
      },
      handler: this.payment_success.bind(this),
      theme: {
        color: '#6466e3'
      },
      modal: {
        ondismiss:  () => {
          console.log('dismissed')
        }
      }
    }

    var rzp1 = new Razorpay(Options);
    rzp1.on('payment.failed', this.payment_failed.bind(this));
    rzp1.open();
  }

  payment_success(response:any) {
    console.log("Payment Success")
    this.paymentService.addOrder(response.razorpay_payment_id, response.razorpay_order_id, this.movieId).subscribe(
      (data) => {
        console.log('Payment order added');
      },
      (error) => {
        console.error('Error ', error);
      }
    )
    this.moviePurchased.emit(true);
    return;
  }

  payment_failed(response:any) {
    this.moviePurchased.emit(false);
    this.paymentService.addOrder(response.error.metadata.payment_id, response.error.metadata.order_id, this.movieId).subscribe(
      (data) => {
        console.log('Payment order failed');
      },
      (error) => {
        console.error('Error ', error);
      }
    )
    alert(response.error.code);
    alert(response.error.description);
    alert(response.error.source);
    alert(response.error.step);
    alert(response.error.reason);
    alert(response.error.metadata.order_id);
    alert(response.error.metadata.payment_id);
  }
}

