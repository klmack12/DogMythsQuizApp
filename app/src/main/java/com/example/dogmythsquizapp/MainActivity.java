package com.example.dogmythsquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        q1 = new Question("Dogs only need tick prevention in the summer.", false);
        q2 = new Question("Dogs can see a range of colors.", true);
        q3 = new Question("A dogs mouth is cleaner than a human’s mouth.", false);
        q4 = new Question("The common cold does not affect dogs.", true);
        q5 = new Question("Some dog breeds are more aggressive than others.", false);
        questions = new Question[] {q1, q2, q3, q4, q5};
        currentIndex = 0;
        currentQ = questions[currentIndex];

                // do something with UI elements - user interface

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if (currentIndex >= questions.length) {
                    message = "End of questions, click VIEW SCORE to see your score.";
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();  //pop-up message
                }
                else {

                    currentQ = questions[currentIndex];
                    String currentQText = currentQ.getQText();
                    qTextTV.setText(currentQText);
                }

            }

        });

                trueBtn.setOnClickListener(new View.OnClickListener() {  //see if this button is clicked
                    @Override
                    public void onClick(View view) {
                        if (currentQ.getCorrectAnswer())
                        {
                            message = "That is correct.";
                            score++;
                        }
                        else
                        {
                            message = "That is incorrect.";
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
                    message = "That is correct.";
                    score++;
                }
                else
                {
                    message = "That is incorrect.";
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