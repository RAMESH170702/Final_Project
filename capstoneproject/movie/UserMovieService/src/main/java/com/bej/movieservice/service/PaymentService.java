package com.bej.movieservice.service;

import com.bej.movieservice.domain.OrderDetails;
import com.bej.movieservice.domain.PaymentDetails;
import com.bej.movieservice.domain.PaymentOrder;
import com.bej.movieservice.domain.User;
import com.bej.movieservice.exception.UserNotFoundException;
import com.razorpay.RazorpayException;

import java.util.List;

public interface PaymentService {
    String createPaymentOrder(PaymentOrder paymentOrder) throws RazorpayException;
    User updatePayment(String userEmail, PaymentDetails paymentDetails) throws RazorpayException, UserNotFoundException;

    List<OrderDetails> getOrders(String userEmail) throws UserNotFoundException;
    boolean isMovieOwned (String userEmail, Long movieId) throws UserNotFoundException;
}
