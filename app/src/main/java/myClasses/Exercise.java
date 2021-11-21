package myClasses;

public class Exercise {

    private String name;
    private int reps_number;
    private String rest_time;

    public Exercise(String name,int reps_number,String rest_time) {
        this.name = name;
        this.reps_number = reps_number;
        this.rest_time = rest_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps_number() {
        return reps_number;
    }

    public void setReps_number(int reps_number) {
        this.reps_number = reps_number;
    }

    public String getRest_time() {
        return rest_time;
    }

    public void setRest_time(String rest_time) {
        this.rest_time = rest_time;
    }
}
