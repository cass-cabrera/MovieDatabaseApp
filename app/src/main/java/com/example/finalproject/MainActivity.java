package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAINACTIVITY_Log";

    Button acct;
    Button login;
    Button rate;
    Button admin;

    user User;
    userControl UserControl;

    Movie movie;
    MovieControl movieControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acct = findViewById(R.id.createAcct);
        login = findViewById(R.id.login);
        rate = findViewById(R.id.rateMovie);
        admin = findViewById(R.id.admin);

        UserControl = userControl.get(this.getApplicationContext());
        movieControl = MovieControl.get(this.getApplicationContext());

        Intent intent  = getIntent();
        String email = intent.getStringExtra("email");
        Intent intent1 = getIntent();
        String password = intent1.getStringExtra("password");

        if(email != null && password != null){
            User = UserControl.isUser(email, password);
        }

        if(User != null) {
            login.setText("Sign Out");
        }
        else {
            login.setText("Sign In");
        }

        if(UserControl.getAllUsers().isEmpty()){
            user user1 = new user(UUID.randomUUID(), "Thanos", "thanos@stone.com", "6Stones", false);
            UserControl.addUser(user1);
            user user2 = new user(UUID.randomUUID(), "Drew", "ilove@marvel.com", "CST338", false);
            UserControl.addUser(user2);
            user user3 = new user(UUID.randomUUID(), "Bob", "notsponge@krustykrab.com", "Gary", false);
            UserControl.addUser(user3);

            Movie  movie1 = new Movie(UUID.randomUUID(), "Dude, Where's My Car?", "December 15, 2000", "Dudes look for car.");
            movieControl.addMovie(movie1);
            Movie  movie2 = new Movie(UUID.randomUUID(), "Spiderman: Homecoming", "July 7, 2017", "Spidey boy fights Vulture.");
            movieControl.addMovie(movie2);
            Movie  movie3 = new Movie(UUID.randomUUID(), "Harry Potter & The Goblet Of Fire", "November 18, 2005", "Harry Potter competes for the Goblet of Fire.");
            movieControl.addMovie(movie3);

        }
    }

    public void createAccount(View view) {
        Intent intent = new Intent(MainActivity.this, createAcctActivity.class);
        startActivity(intent);
    }

    public void logIn(View view) {
        if(User == null) {
            Intent intent = new Intent(MainActivity.this, logInActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void rateMovie(View view) {
        if(User == null) {
            Intent intent = new Intent(MainActivity.this, logInActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(MainActivity.this, rateMovie.class);
            intent.putExtra("email", User.getEmail());
            intent.putExtra("password", User.getPassword());
            startActivity(intent);
        }

    }

    public void adminLogIn(View view) {

        if(User == null) {
            Intent intent = new Intent(MainActivity.this, adminLogin.class);
            startActivity(intent);
        }
        else {
            Toast t = Toast.makeText(this, "SIGN OUT BEFORE ATTEMPTING TO SIGN IN AS ADMIN", Toast.LENGTH_LONG);
            t.show();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra("email", User.getEmail());
            intent.putExtra("password", User.getPassword());
            startActivity(intent);

        }


    }
}
