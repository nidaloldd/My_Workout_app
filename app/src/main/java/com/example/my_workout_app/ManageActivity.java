package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import Adapter.ManageAdapter;
import myClasses.Exercise;
import myClasses.WorkoutPlan;

public class ManageActivity extends AppCompatActivity {
    private Button backButton;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        backButton = findViewById(R.id.backID);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent return_intent = getIntent();
                setResult(RESULT_OK, return_intent);
                finish();
            }
        });

        adapter = new ManageAdapter(this,((MyApplication)this.getApplication()).getAllPlan());
        recyclerView.setAdapter(adapter);

    }
}