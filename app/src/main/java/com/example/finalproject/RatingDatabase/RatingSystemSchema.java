package com.example.finalproject.RatingDatabase;

public class RatingSystemSchema {

    public static final class RatingTable {

        public static final String name = "RATING_TABLE";

        public static final class Cols {
            public final static String MOVIE_UUID = "movieUUID";
            public final static String USER_UUID = "userUUID";
            public final static String RATING = "rating";


        }
    }

}
