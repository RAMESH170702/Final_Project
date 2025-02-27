package com.bej.movieservice.domain;

import java.util.Map;

public class PaymentOrder {
    private long movieId;
    private int amount;

    public PaymentOrder(long movieId, int amount) {
        this.movieId = movieId;
        this.amount = amount;
    }

    public PaymentOrder() {
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentOrder{" +
                "movieId=" + movieId +
                ", amount=" + amount +
                '}';
    }
}
