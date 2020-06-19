package com.example.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener {
    private Button buttonObjectChoice1;
    private Button buttonObjectChoice2;
    private Button buttonObjectChoice3;
    private int correctAnswer;
    private int score = 0;
    private int level = 1;
    private TextView scoret;
    private TextView levelt;
    private TextView num1;
    private TextView num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Start to initialize my variables
        buttonObjectChoice1 = findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = findViewById(R.id.buttonChoice3);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        scoret = findViewById(R.id.Score);
        levelt = findViewById(R.id.level);
        scoret.setText("Score:" + score);
        levelt.setText("Level:" + level);
        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);
        setquestion();

    }

    @Override
    public void onClick(View v) {
        int answergiven = 0;
        switch (v.getId()) {
            case R.id.buttonChoice1:
                answergiven = Integer.parseInt("" + buttonObjectChoice1.getText());
                break;
            case R.id.buttonChoice2:
                answergiven = Integer.parseInt("" + buttonObjectChoice2.getText());
                break;
            case R.id.buttonChoice3:
                answergiven = Integer.parseInt("" + buttonObjectChoice3.getText());
                break;
        }

        updateScoreAndLevel(answergiven);


    }

    public void setquestion() {
        int range = level * 3;
        Random randint = new Random();

        int partA = randint.nextInt(range);
        partA++;


        int partB = randint.nextInt(range);
        partB++;

        int correct = partA * partB;
        int wrong1 = correct + randint.nextInt(50);
        int wrong2 = correct - randint.nextInt(50);

        int buttonlayout = randint.nextInt(3);

        correctAnswer = correct;

        num1.setText("" + partA);
        num2.setText("" + partB);

        switch (buttonlayout) {
            case 0:
                buttonObjectChoice1.setText(String.valueOf(correct));
                buttonObjectChoice2.setText(String.valueOf(wrong1));
                buttonObjectChoice3.setText(String.valueOf(wrong2));
                break;

            case 1:
                buttonObjectChoice1.setText(String.valueOf(wrong1));
                buttonObjectChoice2.setText(String.valueOf(correct));
                buttonObjectChoice3.setText(String.valueOf(wrong2));
                break;

            case 2:
                buttonObjectChoice1.setText(String.valueOf(wrong2));
                buttonObjectChoice2.setText(String.valueOf(wrong1));
                buttonObjectChoice3.setText(String.valueOf(correct));
                break;

        }

    }

    public void updateScoreAndLevel(int answerGiven) {
        if (isCorrect(answerGiven)) {
            for(int i = 1; i <= level; i++) {
                score += i;
            }
            level++;
        } else {
            score = 0;
            level = 1;
        }
        scoret.setText("Score:" + score);
        levelt.setText("Level:" + level);
        setquestion();
    }

    public boolean isCorrect(int answer) {
        if (answer == correctAnswer) {
            Toast.makeText(getApplicationContext(),"Well Done!", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(getApplicationContext(),"Sorry back to level 1",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
