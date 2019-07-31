package com.example.finalproject.MovieDatabase;

public class MovieSchema {

    public static final class MovieTable {
        public static final String name = "MOVIE_TABLE";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String RELEASE_DATE = "releaseDate";
            public static final String DESCRIPTION = "description";
        }
    }
}