package com.bej.movieservice.domain;

import java.util.List;

public class GenreDTO {
    private List<Genre> genres;

    public GenreDTO(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "GenreDTO{" +
                "genres=" + genres +
                '}';
    }

    public GenreDTO() {
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
