package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class actuallyRatingTheMovieActivity extends AppCompatActivity {

    public static final String TAG = "actuallyRateMovie_Log";


    user User;
    userControl UserControl;

    Movie movie;
    MovieControl movieControl;

    RatingSystem ratingSys;
    RatingSystemControl ratingSysControl;

    TextView movieName;
    TextView movieDate;
    TextView movieDescription;
    TextView rating;

    Button rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actually_rating_the_movie);

        UserControl = userControl.get(this.getApplicationContext());
        movieControl = MovieControl.get(this.getApplicationContext());
        ratingSysControl = RatingSystemControl.get(this.getApplicationContext());

        rate = findViewById(R.id.submitRating);
        movieName = findViewById(R.id.movieNameDisplay);
        movieDate = findViewById(R.id.movieDateDisplay);
        movieDescription = findViewById(R.id.movieDescriptionDisplay);
        rating = findViewById(R.id.raitng_input);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        Intent intent1 = getIntent();
        String password = intent1.getStringExtra("password");
        Intent intent2 = getIntent();
        String movieNamez = intent2.getStringExtra("movie");

        if (email != null && password != null) {
            User = UserControl.isUser(email, password);
        }

        movie = movieControl.getMovieByName(movieNamez);
        ratingSys = new RatingSystem();


        String date = movie.getReleaseDate();
        String description = movie.getDescription();
        movieName.setText("Title: " + movieNamez);
        movieDate.setText("Release Date: " + date);
        movieDescription.setText("Description: " + description);
    }

    private boolean isEmpty(TextView textToCheck){
        return textToCheck.getText().toString().trim().length() == 0;
    }

    public void youCanFinallyRateIt(View view) {
        if(isEmpty(rating)) {
            Toast.makeText(this, "Make A Rating", Toast.LENGTH_LONG).show();
        }
        else {
            List<RatingSystem> userMovies = ratingSysControl.killme(User);
            Double ratingz = Double.parseDouble(rating.getText().toString());
            ratingSys.setMovieID(movie.getMovie());
            ratingSys.setUserID(User.getUser());
            ratingSys.setRating(ratingz);
            if(userMovies.contains(ratingSys)) {
                Intent intent = new Intent(actuallyRatingTheMovieActivity.this, viewProfileActivity.class);
                intent.putExtra("email", User.getEmail());
                intent.putExtra("password", User.getPassword());
                startActivity(intent);
            }
            else{
                ratingSysControl.addRating(ratingSys);
                Intent intent = new Intent(actuallyRatingTheMovieActivity.this, viewProfileActivity.class);
                intent.putExtra("email", User.getEmail());
                intent.putExtra("password", User.getPassword());
                startActivity(intent);
            }


        }
    }
}
