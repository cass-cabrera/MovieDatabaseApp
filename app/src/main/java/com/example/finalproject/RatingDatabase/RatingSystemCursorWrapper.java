package com.example.finalproject.RatingDatabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.finalproject.RatingSystem;
import com.example.finalproject.RatingDatabase.RatingSystemSchema.RatingTable;

import java.util.UUID;

public class RatingSystemCursorWrapper extends CursorWrapper {

    public RatingSystemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public RatingSystem getRating(){
        String mUuidString = getString(getColumnIndex(RatingTable.Cols.MOVIE_UUID));
        String uUuidString = getString(getColumnIndex(RatingTable.Cols.USER_UUID));
        Double rating = getDouble(getColumnIndex(RatingTable.Cols.RATING));

        RatingSystem rate = new RatingSystem();

        rate.setMovieID(UUID.fromString(mUuidString));
        rate.setUserID(UUID.fromString(uUuidString));
        rate.setRating(rating);


        return rate;
    }
}