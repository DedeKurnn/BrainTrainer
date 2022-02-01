package com.dedekurnn.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int locationOfCorrectAnswer;
    Button startBtn;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    int score = 0;
    int numOfQuestion = 1;
    TextView questions;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainBtn;
    ConstraintLayout constraintLayout;

    /** WIll be called when user click the play again button on screen.
     *  The button is invisible when the game is not finished.
     *  When this method called, it will reset the timer back to 30s and also reset the
     *  score.
     */
    public void playAgain(View view) {
        score = 0;
        numOfQuestion = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(String.format(Locale.getDefault(), "%d/%d", score, numOfQuestion));
        playAgainBtn.setVisibility(View.INVISIBLE);
        resultTextView.setText(" ");

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText("Time out!");
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void startGame(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong you stupid!");
        }
        newQuestions();
        numOfQuestion++;
        scoreTextView.setText(String.format(Locale.getDefault(), "%d/%d", score, numOfQuestion));
    }

    public void newQuestions() {
        Random rand = new Random();

        int firstNumber = rand.nextInt(51);
        int secondNumber = rand.nextInt(51);

        questions.setText(String.format(Locale.getDefault(),"%d + %d = ?", firstNumber, secondNumber));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(firstNumber + secondNumber);
            } else {
                int wrongAnswer = rand.nextInt(101);
                while (wrongAnswer == firstNumber + secondNumber) {
                    wrongAnswer = rand.nextInt(101);
                }
                answers.add(rand.nextInt(101));
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        button1 = findViewById(R.id.answerBtn1);
        button2 = findViewById(R.id.answerBtn2);
        button3 = findViewById(R.id.answerBtn3);
        button4 = findViewById(R.id.answerBtn4);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questions = findViewById(R.id.questions);
        constraintLayout = findViewById(R.id.gameView);

        constraintLayout.setVisibility(View.INVISIBLE);
        newQuestions();
    }
}
