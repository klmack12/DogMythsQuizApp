package com.example.dogmythsquizapp;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreActivity extends AppCompatActivity {
    TextView  scoreNumberTV, highScoresTV;
    TableLayout tableHighscores;
    EditText myEditText;
    Button emailBtn, sendButton, retrieveButton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference mDatabase;
    ArrayList<Scores> allScores;
    String key;
    int score;
    int i = 0;
    String name;
    TextView NameTV;
    TextView ScoreTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //   image = (ImageView) findViewById(R.id.showPuppy);  show one image
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("message");
        NameTV = (TextView) findViewById(R.id.col1);
        ScoreTV = (TextView) findViewById(R.id.col2);
        myEditText = (EditText) findViewById(R.id.typeName);
        tableHighscores = (TableLayout) findViewById(R.id.highScores);
        //textview for score
        scoreNumberTV = (TextView) findViewById(R.id.scoreNumberTV);
        emailBtn = (Button) findViewById(R.id.emailButton);
        //get Intent and then extra and then show the extra variable from the other screen
        Intent incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("scoreName", 0);
        scoreNumberTV.setText(" " + score);  // make the int a string, because this is necessary
        allScores = new ArrayList<Scores>();
        //textview for testing
        highScoresTV = (TextView) findViewById(R.id.highScoresTV);
        sendButton = (Button) findViewById(R.id.sendButton);
        emailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String subj = getString(R.string.subjTxt);
                String bod = getString(R.string.body1Txt) + " " + score + " " + getString(R.string.body2Txt);
                composeEmail(subj, bod);

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase = FirebaseDatabase.getInstance().getReference("scores");
                key = mDatabase.push().getKey();
                // Write a message to the database

                name = myEditText.getText().toString();
                //myRef.setValue(score);
                Scores newScore = new Scores(name, score);
                mDatabase.child(key).setValue(newScore);  //write new values to database
                highScoresTV.setText(name + " your score of " + score + " was sent to database.");

                //Create a new listener that gets all of the Customers in a single call to the database
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Initialize our customer array


                        // Iterate through all the children in the snapshot, this should be
                        // all the children in the "Scores" object
                        for (DataSnapshot scoresSnapShot : snapshot.getChildren()) {
                            //From our snapshot, get the value of our key/value pair. This value
                            //contains a customer object
                            Scores allScore = scoresSnapShot.getValue(Scores.class);

                            allScores.add(allScore);
                            Log.d("onDataChange()", "New Score: " + allScore.playerName);
                           // highScoresTV.setText(" " + allScores.size());

                        }
                        //sort here
                        Collections.sort(allScores);

                        //show High Scores
                        if (allScores.size() > 0)
                        {
                            for (int currentIndex = 0; currentIndex <=4; currentIndex++) {
                                TableRow row = new TableRow(ScoreActivity.this);
                                TextView nextNameTV = new TextView(ScoreActivity.this);
                                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) NameTV.getLayoutParams();
                                nextNameTV.setLayoutParams(params);
                                TextView nextScoreTV = new TextView(ScoreActivity.this);
                                params = (LinearLayout.LayoutParams) ScoreTV.getLayoutParams();
                                nextScoreTV.setLayoutParams(params);

                                nextNameTV.setText(allScores.get(currentIndex).getPlayerName());
                                nextScoreTV.setText(" " + allScores.get(currentIndex).getPlayerScore());
                                row.addView(nextNameTV);
                                row.addView(nextScoreTV);
                                tableHighscores.addView(row);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });


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