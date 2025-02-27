package com.bej.moviefavoriteservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpokenLanguage {
    @JsonProperty("english_name")
    private String englishName;

//    @JsonProperty("iso_639_1")
//    private String iso6391;

    private String name;

    public SpokenLanguage(String englishName, String name) {
        this.englishName = englishName;
        this.name = name;
    }

    public SpokenLanguage() {
    }
// Constructors, getters, and setters


    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpokenLanguage{" +
                "englishName='" + englishName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
