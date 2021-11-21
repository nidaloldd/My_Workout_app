package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class RestActivity extends AppCompatActivity {

    private long StartTimeInMillis = 0;
    private long mTimeLeftInMillis = StartTimeInMillis;

    private TextView Timer;
    private CountDownTimer mCountDownTimer;
    private Boolean mTimerRunning;
    private Button doneButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        Timer = findViewById(R.id.TimerID);
        doneButton = findViewById(R.id.DoneButtonID);
        StartTimeInMillis = getTimesInMillisecond();
        mTimeLeftInMillis = StartTimeInMillis;
        startTimer();


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void  startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning =false;
                finish();
            }
        }.start();

        mTimerRunning = true;

    }

    private  void  updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis/1000)/60;
        int seconds = (int) (mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        Timer.setText(timeLeftFormatted);
    }

    private int getTimesInMillisecond(){
        Bundle extras = getIntent().getExtras();
        String timeString = extras.getString("getTime");

        int D = timeString.indexOf(":");

        int minutes = Integer.parseInt(timeString.substring(0,D));
        int seconds = Integer.parseInt(timeString.substring(D+1,timeString.length()));

        Toast.makeText(this,String.valueOf(seconds),Toast.LENGTH_LONG).show();

        return minutes*1000*60+seconds*1000;
    }
}


