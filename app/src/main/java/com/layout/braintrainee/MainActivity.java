package com.layout.braintrainee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView resultTextview;
    TextView pointTextview;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextview;
    TextView timerTextview;
    Button playAgainButton;
    RelativeLayout hiddenLayout;

    int locationOfCorrectAns;
    int score = 0;
    int numberOfQuestion = 0;
    int activePlaying = 0;

    public void generateQuestion(){
        Random rand =  new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextview.setText(a+" + "+b);

        locationOfCorrectAns = rand.nextInt(4);
        int incorrectAns;
        ArrayList<Integer> answer = new ArrayList<Integer>();

        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAns){
                answer.add(a+b);
            }else{
                incorrectAns = rand.nextInt(41);
                while (incorrectAns == (a+b)){
                    incorrectAns = rand.nextInt(41);
                }
                answer.add(incorrectAns);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestion = 0;
        activePlaying = 1;

        timerTextview.setText("30s");
        pointTextview.setText("0/0");
        resultTextview.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextview.setText(String.format("%02d", (millisUntilFinished/1000))+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextview.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
                activePlaying = 0;
            }
        }.start();
    }

    public void chooseAnswer(View view){
        if(activePlaying == 1){
            if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){
                score++;
                resultTextview.setText("Correct! Answer");
            }else {
                resultTextview.setText("Wrong Answer");
            }
            resultTextview.setVisibility(View.VISIBLE);
            numberOfQuestion++;
            pointTextview.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
            generateQuestion();
        }/*else{
            Toast.makeText(MainActivity.this, "Press Play Again Button", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        hiddenLayout.setVisibility(View.VISIBLE);

        //call playAgain method....
        playAgain(findViewById(R.id.playAganiButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        sumTextview = (TextView) findViewById(R.id.sumTextview);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextview = (TextView) findViewById(R.id.resultTextview);
        pointTextview = (TextView) findViewById(R.id.pointTextview);
        timerTextview = (TextView) findViewById(R.id.timeTextview);
        playAgainButton = (Button) findViewById(R.id.playAganiButton);
        hiddenLayout = (RelativeLayout) findViewById(R.id.hiddenLayout);

    }
}
