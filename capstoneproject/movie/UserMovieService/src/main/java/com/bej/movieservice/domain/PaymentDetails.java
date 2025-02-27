package com.bej.movieservice.domain;

public class PaymentDetails {
    private long movieId;
    private String orderId;
    private String paymentId;

    public PaymentDetails() {
    }

    public PaymentDetails(long movieId, String orderId, String paymentId) {
        this.movieId = movieId;
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "movieId=" + movieId +
                ", orderId='" + orderId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
