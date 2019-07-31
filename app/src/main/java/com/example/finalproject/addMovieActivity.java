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

public class addMovieActivity extends AppCompatActivity {

    public static final String TAG = "addMovieACTIVITY_Log";

    Button addMovie;
    Button menu;

    TextView name;
    TextView date;
    TextView description;

    MovieControl movieControl;
    Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        addMovie = findViewById(R.id.submitMovieButt);
        menu = findViewById(R.id.menuButtFAdd);
        name = findViewById(R.id.movieNameBox);
        date = findViewById(R.id.movieDateBox);
        description = findViewById(R.id.movieDescriptionBox);

        movieControl = MovieControl.get(this.getApplicationContext());
        movie = new Movie();
    }

    private boolean exist(){
        String namez = name.getText().toString();
        String datez = date.getText().toString();
        String descriptionz = description.getText().toString();

        return movieControl.exist(namez, datez, descriptionz);
    }

    private boolean isEmpty(TextView textToCheck){
        return textToCheck.getText().toString().trim().length() == 0;
    }

    public void addMovie(View view) {
        Log.i(TAG, "add movie button clicked");

        if(isEmpty(name)) {
            Toast.makeText(this, "Make A Selection", Toast.LENGTH_LONG).show();
        }
        else {

            if (!exist()) {
                movie.setName(name.getText().toString());
                movie.setReleaseDate(date.getText().toString());
                movie.setDescription(description.getText().toString());
                movieControl.addMovie(movie);
                Toast t = Toast.makeText(this, "MOVIE ADDED SUCCESSFULLY", Toast.LENGTH_LONG);
                t.show();
            } else {
                Toast t = Toast.makeText(this, "MOVIE ALREADY EXISTS", Toast.LENGTH_LONG);
                t.show();
            }
        }
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(addMovieActivity.this, adminLog.class);
        startActivity(intent);    }
}

