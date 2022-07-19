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

    TextView qText;
    Button trueBtn, falseBtn, scoreBtn;
    String message = " ";
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // initialize your variables - inflate or connecting to xml  (similar to constructor)

        qText = (TextView) findViewById(R.id.questionText);
        trueBtn = (Button) findViewById(R.id.trueButton);
        falseBtn = (Button) findViewById(R.id.falseButton);
        scoreBtn = (Button) findViewById(R.id.scoreButton);
        score = 0;
                // do something with UI elements - user interface

                trueBtn.setOnClickListener(new View.OnClickListener() {  //see if this button is clicked
                    @Override
                    public void onClick(View view) {
                        message = "That is incorrect.";
                        Context context = getApplicationContext();

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, message, duration);
                        toast.show();  //pop-up message
                    }
                });

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "That is correct!";
                score ++;
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
                showScore.putExtra("scoreName",score);
                    //don't type packageContext or name: they appear automatically
                startActivity(showScore);
            }
        });
    }
}