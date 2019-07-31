package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class rateMovieActivity extends AppCompatActivity {

    public static final String TAG = "selectMovie_Log";

    user User;
    userControl UserControl;

    Movie movie;
    MovieControl movieControl;

    Button select;
    AutoCompleteTextView list;

    List<String> movies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_movie2);

        select = findViewById(R.id.movie_select_butt);
        list = findViewById(R.id.movie_option_list);

        UserControl = userControl.get(this.getApplicationContext());
        movieControl = MovieControl.get(this.getApplicationContext());

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        Intent intent1 = getIntent();
        String password = intent1.getStringExtra("password");

        if (email != null && password != null) {
            User = UserControl.isUser(email, password);
        }

        movies = movieControl.getAllMovies();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,movies);
        list.setAdapter(adapter);

        if(movies.isEmpty()){
            Toast.makeText(this, "There are currently no movies to be rated.", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isEmpty(AutoCompleteTextView textToCheck){
        return textToCheck.getText().toString().trim().length() == 0;
    }

    public void rateIt(View view) {
        if(isEmpty(list)){
            Toast.makeText(this, "Please Make Selection", Toast.LENGTH_LONG).show();
        }
        else {
            String movieChoice = list.getText().toString();

            Intent intent = new Intent(rateMovieActivity.this, actuallyRatingTheMovieActivity.class);
            intent.putExtra("email", User.getEmail());
            intent.putExtra("password", User.getPassword());
            intent.putExtra("movie", movieChoice);
            startActivity(intent);
        }


    }

}