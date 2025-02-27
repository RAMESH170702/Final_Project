package com.bej.movieservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CastDetails {
    @JsonProperty("id")
    private int id;
    @JsonProperty("cast")
    private List<CastMember> cast;

    // Add getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CastDetails(int id, List<CastMember> cast) {
        this.id = id;
        this.cast = cast;
    }

    public CastDetails() {
    }

    public List<CastMember> getCast() {
        return cast;
    }

    public void setCast(List<CastMember> cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "CastDetails{" +
                "id=" + id +
                ", cast=" + cast +
                '}';
    }
}
