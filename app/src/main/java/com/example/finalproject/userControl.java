package com.example.finalproject;

import android.content.Context;

import com.example.finalproject.UserDatabase.userHelper;

import java.util.ArrayList;
import java.util.List;

public class userControl {

    private static userControl mUserControl;
    private Context mContext;
    private userHelper mUserHelper;

    public static userControl get(Context context) {
        if(mUserControl == null) {
            mUserControl = new userControl(context);
        }

        return mUserControl;
    }

    private userControl(Context context) {
        mContext = context.getApplicationContext();
        mUserHelper = new userHelper(mContext);
    }

    public long addUser(user user) {
        return mUserHelper.addUser(user);
    }

    public user isUser(String email, String password) {
        List<user> users = mUserHelper.getUser();

        if(users == null){
            return null;
        }
        for(user u : users) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public boolean exist(String email, String password){
        List<user> users = mUserHelper.getUser();

        if(users == null){
            return false;
        }
        for(user u : users) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public List<user> killme() {
        return mUserHelper.getUser();

    }

    public List<String> getAllUsers(){
        List<user> users = mUserHelper.getUser();
        List<String> wow = new ArrayList<>();

        for(user u : users) {
            wow.add(u.getName());
        }

        return wow;
    }


}
