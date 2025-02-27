package com.bej.movieservice.service;

import com.bej.movieservice.domain.OrderDetails;
import com.bej.movieservice.domain.PaymentDetails;
import com.bej.movieservice.domain.PaymentOrder;
import com.bej.movieservice.domain.User;
import com.bej.movieservice.exception.UserAlreadyExistsException;
import com.bej.movieservice.exception.UserNotFoundException;
import com.bej.movieservice.repository.UserMovieRepository;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.aspectj.weaver.ast.Or;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
//    @Value("${razorpay.keyId}")
    private static final String KEY_ID = "rzp_test_sT0jZz5QQMJ6ME" ;

//    @Value("${razorpay.keySecret}")
    private static final String KEY_SECRET = "HL2n8zYiQmOr74KAveyv7uVA";
    private static final String CURRENCY = "INR";

    @Autowired
    private UserMovieRepository userRepository;

    public String createPaymentOrder(PaymentOrder paymentOrder) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(KEY_ID, KEY_SECRET);
        JSONObject order = new JSONObject();
//        Order order = new Order();
        order.put("amount", paymentOrder.getAmount() * 100); // Razorpay expects amount in paisa
        order.put("currency", CURRENCY);
//        order.put("receipt", paymentOrder.getReceiptId());
        Order createdOrder = razorpayClient.orders.create(order);
        System.out.println("Order is " + createdOrder);
        return createdOrder.get("id");
    }
    public User updatePayment(String userEmail, PaymentDetails paymentDetails) throws RazorpayException, UserNotFoundException{
        RazorpayClient razorpay = new RazorpayClient(KEY_ID, KEY_SECRET);
        String orderId = paymentDetails.getOrderId();
        Order order = razorpay.orders.fetch(orderId);
        Payment payment = razorpay.payments.fetch(paymentDetails.getPaymentId());

        OrderDetails orderDetails = new OrderDetails(order.get("id"),
                payment.get("id"),
                order.get("amount").toString(),
                payment.get("method"),
                order.get("status"),
                paymentDetails.getMovieId());

        System.out.println("orderDetails is " + orderDetails);

        if(userRepository.findById(userEmail).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userEmail).get();
        if (user.getOrders().isEmpty()) {
            user.setOrders(Arrays.asList(orderDetails));
        }
        else {
            List<OrderDetails> orders = user.getOrders();
            orders.add(orderDetails);
            user.setOrders(orders);
        }

        return userRepository.save(user);
    }

    public List<OrderDetails> getOrders(String userEmail) throws UserNotFoundException {
        if(userRepository.findById(userEmail).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userEmail).get();
        return user.getOrders();
    }

    @Override
    public boolean isMovieOwned(String userEmail, Long movieId) throws UserNotFoundException {
        boolean isOwned = false;
        if(userRepository.findById(userEmail).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userEmail).get();
        List <OrderDetails> orders = user.getOrders();
        OrderDetails foundOrder = orders.stream()
                .filter(order -> order.getMovieId().equals(movieId))
                .findFirst()
                .orElse(null);

        if (foundOrder != null) {
            isOwned = true;
        }
        return isOwned;
    }
}



