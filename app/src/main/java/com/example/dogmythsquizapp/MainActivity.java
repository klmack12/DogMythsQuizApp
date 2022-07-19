package com.example.dogmythsquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // declare variables for your UI elements

    TextView qTextTV;
    Button trueBtn, falseBtn, scoreBtn, nextBtn;
    String message = " ";
    int score;
    Question q1, q2, q3, q4, q5,  currentQ;
    Question[] questions;
    int currentIndex;
    ImageView image1, image2, image3, image4, image5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // initialize your variables - inflate or connecting to xml  (similar to constructor)

        qTextTV = (TextView) findViewById(R.id.questionText);
        trueBtn = (Button) findViewById(R.id.trueButton);
        falseBtn = (Button) findViewById(R.id.falseButton);
        scoreBtn = (Button) findViewById(R.id.scoreButton);
        nextBtn = (Button) findViewById(R.id.nextButton);
        score = 0;
        q1 = new Question(getString(R.string.question1Text), false);
        q2 = new Question(getString(R.string.q2Text), true);
        q3 = new Question(getString(R.string.q3Text), false);
        q4 = new Question(getString(R.string.q4Text), true);
        q5 = new Question(getString(R.string.q5Text), false);
        questions = new Question[] {q1, q2, q3, q4, q5};
        currentIndex = 0;
        currentQ = questions[currentIndex];

                // do something with UI elements - user interface

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if (currentIndex >= questions.length) {
                    message = getString(R.string.endQText);
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();  //pop-up message
                }
                else {

                    currentQ = questions[currentIndex];
                    String currentQText = currentQ.getQText();  //change the question if not the last one.
                    qTextTV.setText(currentQText);
                    image1 = (ImageView) findViewById(R.id.showNextPuppy);
          //     qTextTV.setText(questions[currentIndex].toString()); This would show what is in the current position of the array
                }

            }

        });

                trueBtn.setOnClickListener(new View.OnClickListener() {  //see if this button is clicked
                    @Override
                    public void onClick(View view) {
                        if (currentQ.getCorrectAnswer())
                        {
                            message = getString(R.string.correctTxt);
                            score++;
                        }
                        else
                        {
                            message = getString(R.string.incorrectText);
                        }

                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, message, duration);
                        toast.show();  //pop-up message
                    }
                });

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentQ.getCorrectAnswer())
                {
                    message = getString(R.string.correctTxt);
                    score++;
                }
                else
                {
                    message = getString(R.string.incorrectText);
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        });

        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showScore = new Intent(MainActivity.this, ScoreActivity.class);
                showScore.putExtra("scoreName",score);  //send a key and a value
                    //don't type packageContext or name: they appear automatically
                startActivity(showScore);
            }
        });
    }
}