package com.example.finalproject.UserDatabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.finalproject.user;
import com.example.finalproject.UserDatabase.userSchema.UserTable;

import java.util.UUID;

public class userCursorWrapper extends CursorWrapper {

    public userCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public user getUser() {
        String uuidString = getString(getColumnIndex(UserTable.Cols.UUID));
        String name = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String password = getString(getColumnIndex(UserTable.Cols.PASSWORD));
        Boolean isAdmin = Boolean.parseBoolean(getString(getColumnIndex(UserTable.Cols.ISADMIN)));

        user user = new user(UUID.fromString(uuidString));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(isAdmin);

        return user;
    }

}
