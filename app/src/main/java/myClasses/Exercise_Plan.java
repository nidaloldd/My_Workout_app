package myClasses;
import java.util.LinkedList;

public class Exercise_Plan {

    LinkedList<Exercise> exercises;
    // "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday";
    private boolean[] days_bool = new boolean[]{false,false,false,false,false,false,false,};

    public Exercise_Plan(LinkedList<Exercise> exercises, boolean[] days_bool) {
        this.exercises = exercises;
        this.days_bool = days_bool;
    }
    public Exercise_Plan(LinkedList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public LinkedList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(LinkedList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public boolean[] getDays_bool() {
        return days_bool;
    }

    public void setDays_bool(boolean[] days_bool) {
        this.days_bool = days_bool;
    }
}
