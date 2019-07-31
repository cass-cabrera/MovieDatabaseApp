package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class viewProfileActivity extends AppCompatActivity {

    public static final String TAG = "viewProfile_Log";


    ListView listView;

    user User;
    userControl UserControl;

    Movie movie;
    MovieControl movieControl;

    RatingSystem ratingSys;
    RatingSystemControl ratingSysControl;

    TextView name;


    Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        listView = findViewById(R.id.movie_list);
        name = findViewById(R.id.displayName);
        home = findViewById(R.id.homeButton);

        UserControl = userControl.get(this.getApplicationContext());
        movieControl = MovieControl.get(this.getApplicationContext());
        ratingSysControl = RatingSystemControl.get(this.getApplicationContext());

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        Intent intent1 = getIntent();
        String password = intent1.getStringExtra("password");

        if (email != null && password != null) {
            User = UserControl.isUser(email, password);
        }
        movie = new Movie();


        name.setText(User.getName() + "'s Profile");

        List<RatingSystem> userMovies = ratingSysControl.killme(User);
        List<Movie> all = movieControl.killme();

        Collections.reverse(userMovies);

        if(userMovies.isEmpty()) {
            Toast.makeText(this,"NO DATA TO SHOW", Toast.LENGTH_LONG).show();
        }
        else {
            List<String> arr = new ArrayList<>();
            List<String> compare = new ArrayList<>();

            for(RatingSystem r : userMovies) {
                for(Movie m : all) {
                    if (r.getMovieID().equals(m.getMovie())) {
                        String word = m.getName();
                        Double rate = r.getRating();
                        if(!compare.contains(word)) {
                            compare.add(word);
                            arr.add(word + " (" + rate + ")");
                        }
                    }
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr);
            listView.setAdapter(adapter);
        }

    }

    public void goHome(View view) {
        Intent intent = new Intent(viewProfileActivity.this, MainActivity.class);
        intent.putExtra("email", User.getEmail());
        intent.putExtra("password", User.getPassword());
        startActivity(intent);
    }
}

