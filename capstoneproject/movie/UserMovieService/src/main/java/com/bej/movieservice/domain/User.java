package com.bej.movieservice.domain;

import com.razorpay.Payment;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    private int userId;
    @Id
    private String userEmail;
    private String userName;
    private String password;
    private String phoneNo;
    private Address address;

    private List<OrderDetails> orders;

    public User(int userId, String userEmail, String userName, String password, String phoneNo, Address address, List<OrderDetails> orders) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.address = address;
        this.orders = orders;
    }

    public User(){ }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public List<OrderDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetails> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address=" + address +
                ", orders=" + orders +
                '}';
    }
}