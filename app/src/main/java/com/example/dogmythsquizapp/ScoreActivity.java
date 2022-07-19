package com.example.dogmythsquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    TextView  scoreNumberTV;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        image = (ImageView) findViewById(R.id.showPuppy);

        //textview for score
        scoreNumberTV = (TextView) findViewById(R.id.scoreNumberTV);

        //get Intent and then extra and then show the extra variable from the other screen
        Intent incomingIntent = getIntent();
        int score = incomingIntent.getIntExtra("scoreName", 0);
        scoreNumberTV.setText("" + score);  // make the int a string, because this is necessary
    }
}