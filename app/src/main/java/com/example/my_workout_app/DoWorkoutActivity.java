package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class DoWorkoutActivity extends AppCompatActivity {

    private SeekBar workoutStateSeekbar;
    private TextView workoutNameText;

    private TextView exerciseNameText;
    private TextView exerciseRepsText;

    private EditText RepsEditText;
    private SeekBar repsSeekBar;
    private Button doneButton;

    private int currentExIndex =0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_workout);

        //Get Data
        Bundle extras = getIntent().getExtras();
        int index = extras.getInt("index");
        String workoutName = extras.getString("name");
        ArrayList<String> ex_name = extras.getStringArrayList("ex_name");
        ArrayList<Integer>ex_reps = extras.getIntegerArrayList("ex_reps");
        ArrayList<Integer>ex_rest = extras.getIntegerArrayList("ex_rest");


        workoutStateSeekbar = findViewById(R.id.WorkoutStateSeekBarID);
        workoutNameText = findViewById(R.id.workoutNameTextID);

        exerciseNameText = findViewById(R.id.exerciseNameTextID);
        exerciseRepsText = findViewById(R.id.exerciseRepsTextID);

        RepsEditText = findViewById(R.id.RepsEditTextID);
        repsSeekBar = findViewById(R.id.repsSeekBarID);
        doneButton = findViewById(R.id.doneButtonID);


        //workoutStateSeekbar.setEnabled(false);
        workoutStateSeekbar.setMax(ex_name.size());
        workoutStateSeekbar.setProgress(currentExIndex);
        workoutNameText.setText(workoutName);
        exerciseNameText.setText(ex_name.get(currentExIndex));
        exerciseRepsText.setText("Try to Do "+ ex_reps.get(currentExIndex)+" Reps");

        //User won't be able to touch and change the progress this way.
        workoutStateSeekbar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        repsSeekBar.setMax(ex_reps.get(index));
        repsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                RepsEditText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //seekBar_percentage = seekBar.getProgress();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentExIndex < ex_name.size()-1){
                    currentExIndex++;
                    workoutStateSeekbar.setProgress(currentExIndex);
                    workoutNameText.setText(workoutName);
                    repsSeekBar.setMax(ex_reps.get(currentExIndex));
                    repsSeekBar.setProgress(0);
                    exerciseNameText.setText(ex_name.get(currentExIndex));
                    exerciseRepsText.setText("Try to Do "+ ex_reps.get(currentExIndex)+" Reps");

                    Intent intent = new Intent(DoWorkoutActivity.this,RestActivity.class);
                    intent.putExtra("getTime", ex_rest.get(currentExIndex));
                    startActivity(intent);

                }
                else{
                    finish();
                }
            }
        });


    }
}