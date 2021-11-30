package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity  extends AppCompatActivity  implements View.OnClickListener {


    private Button manage_plan_button;
    private Button make_plan_button;
    private Button exit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ((MyApplication)this.getApplication()).loadData();

        manage_plan_button = findViewById(R.id.manageButtomID);
        make_plan_button = findViewById(R.id.workoutPlan_ButtonID);
        exit_button = findViewById(R.id.exit_ButtonID);

        manage_plan_button.setOnClickListener(this);
        make_plan_button.setOnClickListener(this);
        exit_button.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.workoutPlan_ButtonID:
                Intent planIntent = new Intent(MainMenuActivity.this,PlaningActivity.class);
                startActivity(planIntent);
                break;
            case R.id.manageButtomID:
                Intent manageIntent = new Intent(MainMenuActivity.this,ManageActivity.class);
                startActivity(manageIntent);
                break;
            case R.id.exit_ButtonID:
                finish();
                System.exit(0);
                break;
        }
    }
    @Override
    public void onBackPressed() {

    }
}