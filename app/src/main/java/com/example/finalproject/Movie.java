package com.example.finalproject;

import java.util.Date;
import java.util.UUID;

public class Movie {

    private UUID movie;
    private String name;
    private String releaseDate;
    private String description;


    public Movie(){
        movie = UUID.randomUUID();
    }

    public Movie(UUID id){
        movie = id;
    }

    public Movie(UUID id, String n, String r, String d) {
        this.movie = id;
        this.name = n;
        this.releaseDate = r;
        this.description = d;
    }


    public UUID getMovie() {
        return movie;
    }

    public void setMovie(UUID movie) {
        this.movie = movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
