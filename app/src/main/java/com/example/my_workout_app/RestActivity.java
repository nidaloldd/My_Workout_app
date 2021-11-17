package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class RestActivity extends AppCompatActivity {
    private Chronometer cronometer;
    private long pauseOffset;
    private boolean runing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        cronometer = findViewById(R.id.cronoMeterID);
        pauseOffset =0;
    }

    public void startCronometer(View view){
        if(!runing){
            cronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            cronometer.start();
            runing = true;
        }
    }
    public void pauseCronometer(View view){
        if(runing){
            cronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime()-cronometer.getBase();
            runing = false;
        }

    }
    public void resetCronometer(View view){
        cronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;
    }
}