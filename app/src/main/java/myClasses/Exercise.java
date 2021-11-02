package myClasses;

public class Exercise {
    private String name;
    private int rest_time;
    private int reps_number;

    public Exercise(String name,int reps_number,int rest_time) {
        this.name = name;
        this.rest_time = rest_time;
        this.reps_number = reps_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRest_time() {
        return rest_time;
    }

    public void setRest_time(int rest_time) {
        this.rest_time = rest_time;
    }

    public int getReps_number() {
        return reps_number;
    }

    public void setReps_number(int reps_number) {
        this.reps_number = reps_number;
    }
}
