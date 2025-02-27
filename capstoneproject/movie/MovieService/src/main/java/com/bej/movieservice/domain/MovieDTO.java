package com.bej.movieservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MovieDTO {
    private int page;
    private List<Movie> results;
    @JsonProperty("total_pages")
    private int totalPages;

    public MovieDTO() {
    }

    @JsonProperty("total_results")
    private int totalResults;

    public MovieDTO(int page, List<Movie> results, int totalPages, int totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "page=" + page +
                ", results=" + results +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}



