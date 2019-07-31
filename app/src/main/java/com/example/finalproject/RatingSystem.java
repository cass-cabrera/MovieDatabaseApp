package com.example.finalproject;

import java.util.UUID;

public class RatingSystem {

    private UUID movieID;
    private UUID userID;
    private Double rating;

    public UUID getMovieID() {
        return movieID;
    }

    public void setMovieID(UUID movieID) {
        this.movieID = movieID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
