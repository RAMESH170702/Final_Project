package com.bej.moviefavoriteservice.domain;

import org.springframework.data.annotation.Id;
public class Genre {

    @Id
    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
