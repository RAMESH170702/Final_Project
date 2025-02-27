package com.bej.moviefavoriteservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "favorites")
public class Favorite {
    @Id
    private String userEmail;
    private List<Long> movieIds;

    public Favorite() {
    }

    public Favorite(String userEmail, List<Long> movieIds) {
        this.userEmail = userEmail;
        this.movieIds = movieIds;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "userEmail='" + userEmail + '\'' +
                ", movieIds=" + movieIds +
                '}';
    }
}
