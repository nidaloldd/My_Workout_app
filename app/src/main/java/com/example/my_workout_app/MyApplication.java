package com.example.my_workout_app;

import android.app.Application;

import java.util.ArrayList;

import myClasses.WorkoutPlan;

public class MyApplication extends Application {
    private ArrayList<WorkoutPlan> allPlan = new ArrayList<WorkoutPlan>() ;

    public ArrayList<WorkoutPlan> getAllPlan() {
        return allPlan;
    }

    public void setAllPlan(ArrayList<WorkoutPlan> allPlan) {
        this.allPlan = allPlan;
    }

    public void addAllPlan(WorkoutPlan workoutPlan) {
        this.allPlan.add(workoutPlan);
    }
}