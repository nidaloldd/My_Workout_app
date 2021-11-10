package com.example.my_workout_app;

import android.app.Application;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.ManageAdapter;
import myClasses.WorkoutPlan;

public class MyApplication extends Application {
    public static ManageAdapter adapter;
    private ArrayList<WorkoutPlan> allPlan = new ArrayList<WorkoutPlan>() ;

    public ArrayList<WorkoutPlan> getAllPlan() {
        return allPlan;
    }

    public void setAllPlan(ArrayList<WorkoutPlan> allPlan) {
        this.allPlan = allPlan;
    }

    public void setIndexAllPlan(WorkoutPlan workoutPlan,int index) {
        this.allPlan.set(index,workoutPlan);
    }

    public void addAllPlan(WorkoutPlan workoutPlan) {
        this.allPlan.add(workoutPlan);
    }
}