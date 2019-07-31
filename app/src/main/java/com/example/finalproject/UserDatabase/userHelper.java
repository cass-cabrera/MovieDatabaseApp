package com.example.finalproject.UserDatabase;

import com.example.finalproject.user;
import com.example.finalproject.UserDatabase.userSchema.UserTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class userHelper extends SQLiteOpenHelper {

    public static final String TAG = "User_LOG";


    public static final String DATABASE_NAME = "userDB.db";
    public static final int VERSION = 2;

    private SQLiteDatabase db;

    public userHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.name + "(" +
                "_id integer primary key autoincrement, " +
                UserTable.Cols.UUID + "," +
                UserTable.Cols.EMAIL + "," +
                UserTable.Cols.USERNAME + "," +
                UserTable.Cols.PASSWORD + "," +
                UserTable.Cols.ISADMIN +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addUser(user user) {
        ContentValues cv = getContentValues(user);
        db = this.getWritableDatabase();

        return db.insert(UserTable.name,null,cv);
    }

    public List<user> getUser() {
        userCursorWrapper cursor = new userCursorWrapper(queryDB(UserTable.name,null,null));

        List<user> users = new ArrayList<>();

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return users;

    }

    private Cursor queryDB(String dbName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();
        try{
            return db.query(
                    UserTable.name,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null
            );
        }catch(Exception e){
            Log.d(TAG, "UserHelper: queryDB didn't find anything...");
            return null;
        }
    }

    private ContentValues getContentValues(user user) {
        ContentValues cv = new ContentValues();
        cv.put(UserTable.Cols.UUID, user.getUser().toString());
        cv.put(UserTable.Cols.USERNAME, user.getName());
        cv.put(UserTable.Cols.EMAIL, user.getEmail());
        cv.put(UserTable.Cols.PASSWORD, user.getPassword());
        cv.put(UserTable.Cols.ISADMIN, String.valueOf(user.isAdmin()));


        return cv;
    }
}
