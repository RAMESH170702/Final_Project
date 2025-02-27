package com.bej.movieservice.domain;

public class OrderDetails {
    private String orderId;
    private String paymentId;
    private String amount;
    private String method;
    private String status;
    private Long movieId;

    public OrderDetails() {
    }

    public OrderDetails(String orderId, String paymentId, String amount, String method, String status, Long movieId) {
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.status = status;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", amount='" + amount + '\'' +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", movieId=" + movieId +
                '}';
    }
}
