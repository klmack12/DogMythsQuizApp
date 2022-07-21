package com.example.dogmythsquizapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ScoreActivity extends AppCompatActivity {
    TextView  scoreNumberTV, highScoresTV;
    ImageView image;
    EditText myEditText;
    Button emailBtn, sendButton, retrieveButton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference mDatabase;
    private ArrayList<Scores> allScores;
    private int currentIndex;
    String key;
    int score;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //   image = (ImageView) findViewById(R.id.showPuppy);  show one image
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("message");

        myEditText = (EditText) findViewById(R.id.typeName);

        //textview for score
        scoreNumberTV = (TextView) findViewById(R.id.scoreNumberTV);
        emailBtn = (Button) findViewById(R.id.emailButton);
        //get Intent and then extra and then show the extra variable from the other screen
        Intent incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("scoreName", 0);
        scoreNumberTV.setText(" " + score);  // make the int a string, because this is necessary

        //textview for testing
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
                        allScores = new ArrayList<Scores>();

                        // Iterate through all the children in the snapshot, this should be
                        // all the children in the "Scores" object
                        for (DataSnapshot scoresSnapShot : snapshot.getChildren()) {
                            //From our snapshot, get the value of our key/value pair. This value
                            //contains a customer object
                            Scores allScore = scoresSnapShot.getValue(Scores.class);

                            allScores.add(allScore);
                            Log.d("onDataChange()", "New Score: " + allScore.playerName);
                            highScoresTV.setText(" " + allScores.size());
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