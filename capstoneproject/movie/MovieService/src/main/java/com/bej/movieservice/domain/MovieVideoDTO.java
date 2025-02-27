package com.bej.movieservice.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class MovieVideoDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("results")
    private List<MovieVideoResultDTO> results;

    // Constructors, getters, and setters
    public MovieVideoDTO(int id, List<MovieVideoResultDTO> results) {
        this.id = id;
        this.results = results;
    }

    public MovieVideoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieVideoResultDTO> getResults() {
        return results;
    }

    public void setResults(List<MovieVideoResultDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MovieVideoDTO{" +
                "id=" + id +
                ", results=" + results +
                '}';
    }
}
