package com.example.my_workout_app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import Adapter.ManageAdapter;
import myClasses.WorkoutPlan;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static ManageAdapter adapter;
    private ArrayList<WorkoutPlan> allPlan = new ArrayList<>() ;

    public ArrayList<WorkoutPlan> getAllPlan() {
        return allPlan;
    }

    public void setIndexAllPlan(WorkoutPlan workoutPlan,int index) {
        this.allPlan.set(index,workoutPlan);
    }
    public void addAllPlan(WorkoutPlan workoutPlan) {
        this.allPlan.add(workoutPlan);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(allPlan);
        editor.putString("task list", json);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<WorkoutPlan>>() {}.getType();
        allPlan = gson.fromJson(json, type);

        if (allPlan == null) {
            allPlan = new ArrayList<>();
        }
    }

}