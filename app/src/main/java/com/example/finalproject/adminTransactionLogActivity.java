package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class adminTransactionLogActivity extends AppCompatActivity {

    public static final String TAG = "viewTransactions_Log";

    ListView listView;

    user User;
    userControl UserControl;

    Movie movie;
    MovieControl movieControl;

    RatingSystem ratingSys;
    RatingSystemControl ratingSysControl;

    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaction_log);

        listView = findViewById(R.id.active_list);
        menu = findViewById(R.id.menuButtFLog);

        UserControl = userControl.get(this.getApplicationContext());
        movieControl = MovieControl.get(this.getApplicationContext());
        ratingSysControl = RatingSystemControl.get(this.getApplicationContext());

        List<RatingSystem> rates = ratingSysControl.killme();
        List<Movie> movies = movieControl.killme();
        List<user> users = UserControl.killme();

        Collections.reverse(rates);

        if(rates.isEmpty()) {
            Toast.makeText(this,"NO DATA TO SHOW", Toast.LENGTH_LONG).show();
        }
        else {
            List<String> arr = new ArrayList<>();
            for(RatingSystem r : rates) {
                for(user u : users) {
                    if(r.getUserID().equals(u.getUser())) {
                        for (Movie m : movies) {
                            if (r.getMovieID().equals(m.getMovie())){
                                String word = u.getName() + " rated " + m.getName() + " a score of " + r.getRating() + " out of 10.";
                                arr.add(word);
                            }
                        }
                    }
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr);
            listView.setAdapter(adapter);
        }
    }

    public void goToMenuFromLog(View view) {
        Intent intent = new Intent(adminTransactionLogActivity.this, adminLog.class);
        startActivity(intent);
    }
}

/*


 */