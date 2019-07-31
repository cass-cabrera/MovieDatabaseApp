package com.example.finalproject.MovieDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.finalproject.Movie;
import com.example.finalproject.MovieDatabase.MovieSchema.MovieTable;

import java.util.ArrayList;
import java.util.List;

public class MovieHelper extends SQLiteOpenHelper {

    public static final String TAG = "Movie_LOG";

    public static final String DATABASE_NAME = "movieDB.db";
    public static final int VERSION = 1;

    private SQLiteDatabase db;

    public MovieHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MovieTable.name + "(" +
                "_id integer primary key autoincrement, " +
                MovieTable.Cols.UUID + "," +
                MovieTable.Cols.NAME + "," +
                MovieTable.Cols.RELEASE_DATE + "," +
                MovieTable.Cols.DESCRIPTION +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addMovie(Movie movie) {
        ContentValues cv = getContentValues(movie);
        db = this.getWritableDatabase();
        return db.insert(MovieTable.name,null,cv);
    }

    public List<Movie> getMovie() {
        MovieCursorWrapper cursor = new MovieCursorWrapper(queryDB(MovieTable.name,null,null));

        List<Movie> movies = new ArrayList<>();

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                movies.add(cursor.getMovie());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return movies;
    }

    private Cursor queryDB(String dbName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();
        try {
            return db.query(
                    MovieTable.name,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null
            );
        } catch (Exception e) {
            Log.d(TAG, "MovieHelper: queryDB didn't find anything...");
            return null;
        }
    }

    private ContentValues getContentValues(Movie movie) {
        ContentValues cv = new ContentValues();
        cv.put(MovieTable.Cols.UUID, movie.getMovie().toString());
        cv.put(MovieTable.Cols.NAME, movie.getName());
        cv.put(MovieTable.Cols.RELEASE_DATE, movie.getReleaseDate());
        cv.put(MovieTable.Cols.DESCRIPTION, movie.getDescription());

        return cv;
    }

    public boolean remove(String uuidString) {
        try {
            db = this.getWritableDatabase();
            db.delete(MovieTable.name, MovieTable.Cols.UUID + " = ?", new String[]{uuidString});
            //guess what? we are preventing SQL injection!\
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
