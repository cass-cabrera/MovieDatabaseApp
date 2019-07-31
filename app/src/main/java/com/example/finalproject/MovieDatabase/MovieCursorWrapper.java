package com.example.finalproject.MovieDatabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.finalproject.Movie;

import java.util.UUID;


public class MovieCursorWrapper extends CursorWrapper {

    public MovieCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Movie getMovie() {
        String uuidString = getString(getColumnIndex(MovieSchema.MovieTable.Cols.UUID));
        String name = getString(getColumnIndex(MovieSchema.MovieTable.Cols.NAME));
        String releaseDate = getString(getColumnIndex(MovieSchema.MovieTable.Cols.RELEASE_DATE));
        String description = getString(getColumnIndex(MovieSchema.MovieTable.Cols.DESCRIPTION));

        Movie movie = new Movie(UUID.fromString(uuidString));
        movie.setName(name);
        movie.setReleaseDate(releaseDate);
        movie.setDescription(description);

        return movie;
    }
}
