package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class adminLog extends AppCompatActivity {

    public static final String TAG = "adminLogACTIVITY_Log";

    Button option1;
    Button option2;
    Button delete;
    Button menu;

    MovieControl movieControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log);

        option1 = findViewById(R.id.admin_log_butt1);
        option2 = findViewById(R.id.admin_log_butt2);
        delete = findViewById(R.id.deleteButt);
        menu = findViewById(R.id.signOutFromAdmin);

        movieControl = MovieControl.get(this.getApplicationContext());
    }

    public void addMovie(View view) {
        Log.i(TAG, "admin add movie button clicked");

        Intent intent = new Intent(adminLog.this, addMovieActivity.class);
        startActivity(intent);
    }

    public void viewLog(View view) {
        Log.i(TAG, "admin view log button clicked");

        Intent intent = new Intent(adminLog.this, adminTransactionLogActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Log.i(TAG, "admin delete movie button clicked");

        Intent intent = new Intent(adminLog.this, DeleteMovieActivity.class);
        startActivity(intent);
    }

    public void signout(View view){
        Intent intent = new Intent(adminLog.this, MainActivity.class);
        startActivity(intent);
    }
}