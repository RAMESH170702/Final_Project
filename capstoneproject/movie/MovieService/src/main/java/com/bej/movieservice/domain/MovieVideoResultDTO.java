package com.bej.movieservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieVideoResultDTO {
    @JsonProperty("iso_639_1")
    private String iso639_1;

    @JsonProperty("iso_3166_1")
    private String iso3166_1;

    @JsonProperty("name")
    private String name;

    @JsonProperty("key")
    private String key;

    @JsonProperty("site")
    private String site;

    @JsonProperty("size")
    private int size;

    @JsonProperty("type")
    private String type;

    @JsonProperty("official")
    private boolean official;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonProperty("id")
    private String videoId;

    public MovieVideoResultDTO(String iso639_1, String iso3166_1, String name, String key, String site, int size, String type, boolean official, String publishedAt, String videoId) {
        this.iso639_1 = iso639_1;
        this.iso3166_1 = iso3166_1;
        this.name = name;
        this.key = key;
        this.site = site;
        this.size = size;
        this.type = type;
        this.official = official;
        this.publishedAt = publishedAt;
        this.videoId = videoId;
    }

    public MovieVideoResultDTO() {
    }
    // Constructors, getters, and setters

    public String getIso639_1() {
        return iso639_1;
    }

    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    public String getIso3166_1() {
        return iso3166_1;
    }

    public void setIso3166_1(String iso3166_1) {
        this.iso3166_1 = iso3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "MovieVideoResultDTO{" +
                "iso639_1='" + iso639_1 + '\'' +
                ", iso3166_1='" + iso3166_1 + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", site='" + site + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", official=" + official +
                ", publishedAt='" + publishedAt + '\'' +
                ", videoId='" + videoId + '\'' +
                '}';
    }
}
