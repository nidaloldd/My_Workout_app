package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Adapter.ManageAdapter;
import myClasses.WorkoutPlan;

public class ManageActivity extends AppCompatActivity {
    private Button backButton;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences ;
    private ArrayList<WorkoutPlan> Data;

    public  static final String SHARED_PREF = "shared_pref";
    public  static final String textMessage = "text";
    //public RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);


        backButton = findViewById(R.id.backID);
        recyclerView = findViewById(R.id.recyclerID);
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
        MyApplication.adapter = new ManageAdapter(this,((MyApplication)this.getApplication()).getAllPlan());
        recyclerView.setAdapter(MyApplication.adapter);

    }
}