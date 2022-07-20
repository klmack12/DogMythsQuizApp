package com.example.dogmythsquizapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    TextView  scoreNumberTV, highScoresTV;
    ImageView image;
    Button emailBtn, sendButton, retrieveButton;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        image = (ImageView) findViewById(R.id.showPuppy);

        //textview for score
        scoreNumberTV = (TextView) findViewById(R.id.scoreNumberTV);
        emailBtn = (Button) findViewById(R.id.emailButton);
        //get Intent and then extra and then show the extra variable from the other screen
        Intent incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("scoreName", 0);
        scoreNumberTV.setText(" " + score);  // make the int a string, because this is necessary

        //textview for high scores
        highScoresTV = (TextView) findViewById(R.id.highScoresTV);

        sendButton = (Button) findViewById(R.id.sendButton);
        retrieveButton = (Button) findViewById(R.id.retrieveButton);

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subj = getString(R.string.subjTxt);
                String bod = getString(R.string.body1Txt) + " " + score + " " + getString(R.string.body2Txt);
                composeEmail(subj, bod);
            }
        });


    }

    public void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }

    }
}