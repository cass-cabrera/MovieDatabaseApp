package com.example.finalproject;

import android.content.Context;

import com.example.finalproject.MovieDatabase.MovieHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieControl {

    private static MovieControl mMovieControl;
    private Context mContext;
    private MovieHelper mMovieHelper;

    public static MovieControl get(Context context) {
        if(mMovieControl == null) {
            mMovieControl = new MovieControl(context);
        }
        return mMovieControl;
    }

    private  MovieControl(Context context) {
        mContext =  context.getApplicationContext();
        mMovieHelper = new MovieHelper(mContext);
    }

    public long addMovie(Movie movie) {
        return mMovieHelper.addMovie(movie);
    }

    public boolean exist(String name, String date, String description){
        List<Movie> movies = mMovieHelper.getMovie();

        if(movies == null){
            return false;
        }
        for(Movie m : movies) {
            if(m.getName().equals(name) && m.getReleaseDate().equals(date) && m.getDescription().equals(description)){
                return true;
            }
        }
        return false;
    }

    public List<String> getAllMovies(){
        List<Movie> movies = mMovieHelper.getMovie();
        List<String> wow = new ArrayList<>();

        for(Movie m : movies) {
            wow.add(m.getName());
        }

        return wow;
    }

    public Movie getMovieByName(String name) {
        List<Movie> movies = mMovieHelper.getMovie();

        for(Movie m : movies){
            if(m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
    public Movie getMovieByID(UUID id) {
        List<Movie> movies = mMovieHelper.getMovie();

        for(Movie m : movies){
            if(m.getMovie() == id) {
                return m;
            }
        }
        return null;
    }
    public List<Movie> killme() {
        return mMovieHelper.getMovie();

    }

    public boolean remove(String uuidString) {
        return mMovieHelper.remove(uuidString);
    }

    public UUID getUUIDbyName(String name) {
        List<Movie> movies = mMovieHelper.getMovie();

        for(Movie m : movies) {
            if(m.getName().equals(name)) {
                return m.getMovie();
            }
        }

        return null;
    }






}
