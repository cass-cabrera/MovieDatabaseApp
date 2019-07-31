package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class logInActivity extends AppCompatActivity {

    public static final String TAG = "loginACTIVITY_Log";

    Button logIn;

    TextView userLogin;
    TextView userPassword;

    StringBuilder sb = new StringBuilder();

    userControl UserControl;
    user User;

    long rowid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logIn = findViewById(R.id.login_button);

        userLogin = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);

        UserControl = userControl.get(this.getApplicationContext());
        User = new user();
    }

    private user getUserInfoFromUser(){
        String email = userLogin.getText().toString();
        String password = userPassword.getText().toString();

        return UserControl.isUser(email, password);
    }

    public void logIn(View view) {
        Log.i(TAG, "login button clicked");

        User = getUserInfoFromUser();

        if(User == null) {
            Toast t = Toast.makeText(this, "USER DOESN'T EXIST", Toast.LENGTH_LONG);
            t.show();
            Intent intent = new Intent(logInActivity.this, createAcctActivity.class);
            startActivity(intent);
        }
        else {
            Toast t = Toast.makeText(this, "LOGGED IN", Toast.LENGTH_LONG);
            t.show();

            Intent intent = new Intent(logInActivity.this, MainActivity.class);
            intent.putExtra("email", User.getEmail());
            intent.putExtra("password", User.getPassword());
            startActivity(intent);
        }
    }
}



