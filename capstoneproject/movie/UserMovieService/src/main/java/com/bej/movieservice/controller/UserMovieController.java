package com.bej.movieservice.controller;

import com.bej.movieservice.domain.OrderDetails;
import com.bej.movieservice.domain.PaymentDetails;
import com.bej.movieservice.domain.PaymentOrder;
import com.bej.movieservice.domain.User;
import com.bej.movieservice.exception.MovieNotFoundException;
import com.bej.movieservice.exception.UserAlreadyExistsException;
//import com.bej.movieservice.service.EmailServiceImpl;
import com.bej.movieservice.exception.UserNotFoundException;
import com.bej.movieservice.service.EmailServiceImpl;
import com.bej.movieservice.service.PaymentService;
import com.bej.movieservice.service.UserMovieService;
import com.razorpay.RazorpayException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.mail.MessagingException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v2")
public class UserMovieController {
    private UserMovieService userMovieService;
    private PaymentService paymentService;
    private ResponseEntity<?> responseEntity;
    private EmailServiceImpl emailService;

    @Autowired
    public UserMovieController(UserMovieService userMovieService, EmailServiceImpl emailService, PaymentService paymentService) {
        this.userMovieService = userMovieService;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    @PostMapping("registers")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException, MessagingException{
        try {
            responseEntity =  new ResponseEntity<>(userMovieService.registerUser(user), HttpStatus.CREATED);
            emailService.sendThankYouEmail(user.getUserEmail());
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch(UserAlreadyExistsException e)
        {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
//        try {
//            User registeredUser = userMovieService.registerUser(user);
//            emailService.sendThankYouEmail(user.getUserEmail());
//            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
//        } catch (UserAlreadyExistsException e) {
//            throw new UserAlreadyExistsException();
//        } catch (MessagingException e) {
//            throw new RuntimeException("Error sending thank you email", e);
//        }
    }

    @GetMapping("/recommendations/{movieId}")
    public ResponseEntity<?> getAllRecommendedMovies(@PathVariable Long movieId) throws MovieNotFoundException{
        try {
            responseEntity = new ResponseEntity<>(userMovieService.getAllRecommendedMovies(movieId), HttpStatus.OK);
        }
        catch (MovieNotFoundException e)
        {
            throw new MovieNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token).getBody();
        System.out.println("User id is " + claims.getSubject());
        String currentUserEmailId = (String)claims.getSubject();
        return new ResponseEntity<>(userMovieService.getCurrentUserDetails(currentUserEmailId), HttpStatus.OK);
    }
//    @GetMapping("/user/{userEmail}")
//    public String getUsernameByEmail(@PathVariable String userEmail) {
//        return userMovieService.getUsernameByEmail(userEmail);
//    }

    @PostMapping("/user/order")
    public ResponseEntity<String> getOrderId(@RequestBody PaymentOrder request) throws RazorpayException {
        String orderId = "";
        try {
            orderId = paymentService.createPaymentOrder(request);
        } catch (RazorpayException e) {
            throw new RazorpayException(e.getMessage());
        }

        return new ResponseEntity<String>(orderId, HttpStatus.OK);
    }

    @PostMapping("/user/payment")
    public ResponseEntity<User> updatePayment(HttpServletRequest request, @RequestBody PaymentDetails paymentRequest) throws UserNotFoundException, RazorpayException {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token).getBody();
        System.out.println("User id is " + claims.getSubject());
        String currentUserEmailId = (String)claims.getSubject();
        User user;
        try {
            user = paymentService.updatePayment(currentUserEmailId, paymentRequest);
        } catch (RazorpayException e) {
            throw new RazorpayException(e.getMessage());
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/orders")
    public ResponseEntity<List<OrderDetails>> getOrders(HttpServletRequest request) throws UserNotFoundException {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token).getBody();
        String currentUserEmailId = (String)claims.getSubject();
        List<OrderDetails> orders;
        try {
            orders = paymentService.getOrders(currentUserEmailId);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<List<OrderDetails>>(orders, HttpStatus.OK);
    }

    @GetMapping("/user/owned/{movieId}")
    public ResponseEntity<?> getMovieOwnedStatus(HttpServletRequest request, @PathVariable Long movieId) throws UserNotFoundException {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(token).getBody();
        String currentUserEmailId = (String)claims.getSubject();
        boolean isOwned = false;
        try {
            isOwned = paymentService.isMovieOwned(currentUserEmailId, movieId);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }

        return new ResponseEntity<>(isOwned, HttpStatus.OK);
    }
}
