package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class adminLogin extends AppCompatActivity {

    public static final String TAG = "adminLoginACTIVITY_Log";


    Button logIn;

    TextView email;
    TextView password;

    StringBuilder sb = new StringBuilder();

    userControl UserControl;
    user User;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        logIn = findViewById(R.id.adminLoginBut);

        email = findViewById(R.id.adminEmail);
        password = findViewById(R.id.adminPassword);

        UserControl = userControl.get(this.getApplicationContext());
        User = new user();
    }

    private user getUserInfoFromUser(){
        String emailz = email.getText().toString();
        String passwordz = password.getText().toString();

        return UserControl.isUser(emailz, passwordz);
    }
    public void adminLogin(View view) {

        Log.i(TAG, "admin login button clicked");

        User = getUserInfoFromUser();

        if(User == null) {
            Toast t = Toast.makeText(this, "USER DOESN'T EXIST", Toast.LENGTH_LONG);
            t.show();
            Intent intent = new Intent(adminLogin.this, createAcctActivity.class);
            startActivity(intent);
        }
        else if(User.isAdmin()){
            Toast t = Toast.makeText(this, "LOGGED IN", Toast.LENGTH_LONG);
            t.show();
            Intent intent = new Intent(adminLogin.this, adminLog.class);
            startActivity(intent);
        }
        else{
            Toast t = Toast.makeText(this, "NOT AN ADMIN", Toast.LENGTH_LONG);
            t.show();
        }


    }
}

