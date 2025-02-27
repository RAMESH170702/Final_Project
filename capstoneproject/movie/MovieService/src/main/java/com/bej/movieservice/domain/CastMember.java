package com.bej.movieservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CastMember {
    private boolean adult;
    private int gender;
    private int id;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    private String name;
    @JsonProperty("original_name")
    private String originalName;
    private double popularity;
    @JsonProperty("profile_path")
    private String profilePath;
    @JsonProperty("cast_id")
    private int castId;
    private String character;
    @JsonProperty("credit_id")
    private String creditId;
    private int order;

    public CastMember() {
    }

    public CastMember(boolean adult, int gender, int id, String knownForDepartment, String name, String originalName, double popularity, String profilePath, int castId, String character, String creditId, int order) {
        this.adult = adult;
        this.gender = gender;
        this.id = id;
        this.knownForDepartment = knownForDepartment;
        this.name = name;
        this.originalName = originalName;
        this.popularity = popularity;
        this.profilePath = profilePath;
        this.castId = castId;
        this.character = character;
        this.creditId = creditId;
        this.order = order;
    }

    // Add getters and setters

    @JsonProperty("adult")
    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    @JsonProperty("gender")
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("known_for_department")
    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("original_name")
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @JsonProperty("popularity")
    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @JsonProperty("profile_path")
    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @JsonProperty("cast_id")
    public int getCastId() {
        return castId;
    }

    public void setCastId(int castId) {
        this.castId = castId;
    }

    @JsonProperty("character")
    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @JsonProperty("credit_id")
    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    @JsonProperty("order")
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CastMember{" +
                "adult=" + adult +
                ", gender=" + gender +
                ", id=" + id +
                ", knownForDepartment='" + knownForDepartment + '\'' +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", popularity=" + popularity +
                ", profilePath='" + profilePath + '\'' +
                ", castId=" + castId +
                ", character='" + character + '\'' +
                ", creditId='" + creditId + '\'' +
                ", order=" + order +
                '}';
    }
}
