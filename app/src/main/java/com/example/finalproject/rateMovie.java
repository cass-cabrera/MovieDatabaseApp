package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rateMovie extends AppCompatActivity {

    public static final String TAG = "rateMovie_Log";


    user User;
    userControl UserControl;

    Button rate;
    Button profile;
    Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie);

        rate = findViewById(R.id.rate_movie_butt);
        profile = findViewById(R.id.view_profile_butt);
        home = findViewById(R.id.goHomeFromRating);

        UserControl = userControl.get(this.getApplicationContext());

        Intent intent  = getIntent();
        String email = intent.getStringExtra("email");
        Intent intent1 = getIntent();
        String password = intent1.getStringExtra("password");

        if(email != null && password != null){
            User = UserControl.isUser(email, password);
        }

    }

    public void rateDaMovie(View view) {
        Intent intent = new Intent(rateMovie.this, rateMovieActivity.class);
        intent.putExtra("email", User.getEmail());
        intent.putExtra("password", User.getPassword());
        startActivity(intent);
    }


    public void viewProfile(View view) {
        Intent intent = new Intent(rateMovie.this, viewProfileActivity.class);
        intent.putExtra("email", User.getEmail());
        intent.putExtra("password", User.getPassword());
        startActivity(intent);
    }

    public void IWannaGOHOME(View view) {
        Intent intent = new Intent(rateMovie.this, MainActivity.class);
        intent.putExtra("email", User.getEmail());
        intent.putExtra("password", User.getPassword());
        startActivity(intent);
    }
}
