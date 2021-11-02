package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button make_plan_button;
    private Button exit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        make_plan_button = findViewById(R.id.workoutPlan_ButtonID);
        exit_button = findViewById(R.id.exit_ButtonID);

        make_plan_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.workoutPlan_ButtonID:
                Intent intent = new Intent(MainMenuActivity.this,PlaningActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_ButtonID:
                finish();
                System.exit(0);
                break;
        }

    }
}