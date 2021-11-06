package myClasses;
import java.util.ArrayList;

public class WorkoutPlan {

    private String name;
    private ArrayList<Exercise> exercises;
    // "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday";
    private Boolean[] days_bool = new Boolean[]{};

    public WorkoutPlan(String name, ArrayList<Exercise> exercises, Boolean[] days_bool) {
        this.name = name;
        this.exercises = exercises;
        this.days_bool = days_bool;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Boolean[] getDays_bool() {
        return days_bool;
    }

    public void setDays_bool(Boolean[] days_bool) {
        this.days_bool = days_bool;
    }
}
