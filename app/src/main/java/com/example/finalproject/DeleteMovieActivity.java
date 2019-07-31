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
import java.util.List;

public class DeleteMovieActivity extends AppCompatActivity {

    public static final String TAG = "deleteMovie_Log";

    Button menu;

    ListView listView;
    TextView choice;

    user User;
    userControl UserControl;

    Movie movie;
    MovieControl movieControl;

    RatingSystem ratingSys;
    RatingSystemControl ratingSysControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_movie);

        listView = findViewById(R.id.active_list);
        choice = findViewById(R.id.deletionSelection);
        menu = findViewById(R.id.menuButtFDelete);

        UserControl = userControl.get(this.getApplicationContext());
        movieControl = MovieControl.get(this.getApplicationContext());
        ratingSysControl = RatingSystemControl.get(this.getApplicationContext());

        List<Movie> movies = movieControl.killme();
        if(movies.isEmpty()) {
            Toast.makeText(this,"NO DATA TO SHOW", Toast.LENGTH_LONG).show();
        }
        else {
            List<String> arr = new ArrayList<>();
            for(Movie m : movies) {
                String word = m.getName();
                arr.add(word);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr);
            listView.setAdapter(adapter);
        }

    }

    private boolean isEmpty(TextView textToCheck){
        return textToCheck.getText().toString().trim().length() == 0;
    }

    public void deleteThisShit(View view) {
        if(isEmpty(choice)) {
            Toast.makeText(this, "Make A Selection", Toast.LENGTH_LONG).show();

        }
        else {
            String word = movieControl.getUUIDbyName(choice.getText().toString()).toString();
            if(word == null) {
                Toast.makeText(this, "The Movie You Entered Does Not Exist", Toast.LENGTH_LONG).show();
            }
            else {
                movieControl.remove(word);
                Toast.makeText(this, "Movie Deleted Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DeleteMovieActivity.this, DeleteMovieActivity.class);
                startActivity(intent);
            }
        }
    }

    public void goToMenuFromDelete(View view) {
        Intent intent = new Intent(DeleteMovieActivity.this, adminLog.class);
        startActivity(intent);
    }
}
