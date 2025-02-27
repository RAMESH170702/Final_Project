package com.bej.moviefavoriteservice.domain;

public class User {
    private String userEmail;

    public User() {
    }
    public User(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserId(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail=" + userEmail +
                '}';
    }
}
