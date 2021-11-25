package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.Locale;

public class RestActivity extends AppCompatActivity {

    private long StartTimeInMillis = 0;
    private long mTimeLeftInMillis = StartTimeInMillis;

    private TextView Timer;
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


        doneButton.setOnClickListener(view -> finish());

    }
    private void  startTimer(){
        CountDownTimer mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
                doneButton.setText("Next exercise");
            }
        }.start();

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
        int seconds = Integer.parseInt(timeString.substring(D+1));

        Toast.makeText(this,String.valueOf(seconds),Toast.LENGTH_LONG).show();

        return minutes*1000*60+seconds*1000;
    }
    @Override
    public void onBackPressed() {

    }
}


