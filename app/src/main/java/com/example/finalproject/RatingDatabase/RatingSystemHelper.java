package com.example.finalproject.RatingDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.finalproject.RatingDatabase.RatingSystemSchema.RatingTable;
import com.example.finalproject.RatingSystem;

import java.util.ArrayList;
import java.util.List;

public class RatingSystemHelper extends SQLiteOpenHelper {

    public static final String TAG = "Rating_LOG";

    public static final String DATABASE_NAME = "ratingSystemDB.db";
    public static final int VERSION = 1;

    private SQLiteDatabase db;

    public RatingSystemHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RatingTable.name + "(" +
                "_id integer primary key autoincrement, " +
                RatingTable.Cols.MOVIE_UUID + "," +
                RatingTable.Cols.USER_UUID + "," +
                RatingTable.Cols.RATING +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addRating(RatingSystem rate) {
        ContentValues cv = getContentValues(rate);
        db = this.getWritableDatabase();

        return db.insert(RatingTable.name,null,cv);
    }

    public List<RatingSystem> getRating() {
        RatingSystemCursorWrapper cursor = new RatingSystemCursorWrapper(queryDB(RatingTable.name,null,null));

        List<RatingSystem> ratings = new ArrayList<>();

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                ratings.add(cursor.getRating());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return ratings;

    }
    private Cursor queryDB(String dbName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();
        try{
            return db.query(
                    RatingTable.name,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null
            );
        }catch(Exception e){
            Log.d(TAG, "RatingHelper: queryDB didn't find anything...");
            return null;
        }
    }

    private ContentValues getContentValues(RatingSystem rate) {
        ContentValues cv = new ContentValues();
        cv.put(RatingTable.Cols.MOVIE_UUID, rate.getMovieID().toString());
        cv.put(RatingTable.Cols.USER_UUID, rate.getUserID().toString());
        cv.put(RatingTable.Cols.RATING, rate.getRating());

        return cv;
    }

}