package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import myClasses.Exercise;
import myClasses.WorkoutPlan;

public class PlaningActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText addName;
    private EditText addReps;
    private EditText workoutName;
    private Button addExerciseButton;
    private Button backButton;
    private Button saveButton;
    private ArrayList<TextView> TV ; // TextViews
    private ArrayList<Button> D_BT ; // Delete_Buttons
    private ArrayList<Button> E_BT ; // Edit_Buttons
    private RelativeLayout relativeLayout_for_texts;
    private RelativeLayout relativeLayout_for_bottoms;
    private CheckBox cb_1,cb_2,cb_3,cb_4,cb_5,cb_6,cb_7;

    private NumberPicker minutesPicker;
    private NumberPicker secondsPicker;

    private ArrayList<Exercise> exercises ;
    private boolean[] days = new boolean[]{};
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planing);
        addName = findViewById(R.id.add_nameID);
        addReps = findViewById(R.id.add_repsID);
        minutesPicker = findViewById(R.id.minute_pickerID);
        secondsPicker = findViewById(R.id.secund_pickerID);
        workoutName = findViewById(R.id.get_nameID);
        addExerciseButton = findViewById(R.id.add_exercise_buttonID);
        TV = new ArrayList<TextView>();
        D_BT = new ArrayList<Button>();
        E_BT = new ArrayList<Button>();
        backButton = findViewById(R.id.back_buttonID);
        saveButton = findViewById(R.id.save_buttonID);
        relativeLayout_for_texts = findViewById(R.id.RL_for_textsID);
        relativeLayout_for_bottoms = findViewById(R.id.RL_for_ButtomsID);

        exercises = new ArrayList<Exercise>();
        days = new boolean[7];

        int a =60;
        String[] data = new String[a];
        for (int i =0; i<a; i++){
            data[i] = String.valueOf(i);
        }
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(data.length-1);
        minutesPicker.setDisplayedValues(data);
        secondsPicker.setMinValue(0);
        secondsPicker.setMaxValue(data.length-1);
        secondsPicker.setDisplayedValues(data);


        cb_1 = findViewById(R.id.cb_1_ID);
        cb_2 = findViewById(R.id.cb_2_ID);
        cb_3 = findViewById(R.id.cb_3_ID);
        cb_4 = findViewById(R.id.cb_4_ID);
        cb_5 = findViewById(R.id.cb_5_ID);
        cb_6 = findViewById(R.id.cb_6_ID);
        cb_7 = findViewById(R.id.cb_7_ID);


        extras = getIntent().getExtras();
        if (extras!= null){
            workoutName.setText(extras.getString("name"));
            set_checkBoxes(extras.getBooleanArray("days"));

            ArrayList<String> ex_name = extras.getStringArrayList("ex_name");
            ArrayList<Integer>ex_reps = extras.getIntegerArrayList("ex_reps");
            ArrayList<String>ex_rest = extras.getStringArrayList("ex_rest");

            exercises.clear();
            for(int i=0;i<ex_name.size();i++){
                Exercise ex = new Exercise(ex_name.get(i),ex_reps.get(i),ex_rest.get(i));
                exercises.add(ex);
            }
            ReDrawLayout();
        }



        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString();
                String reps = addReps.getText().toString();
                String rest = minutesPicker.getValue()+":"+secondsPicker.getValue();

                if      (name.length()==0){Toast.makeText(PlaningActivity.this,"you must name your exercise",Toast.LENGTH_LONG).show();}
                else if (reps.length()==0){Toast.makeText(PlaningActivity.this,"Please enter the number of reps",Toast.LENGTH_LONG).show();}
                else if (name.length()>50){Toast.makeText(PlaningActivity.this,"too much character for an exercise name",Toast.LENGTH_LONG).show();}
                else if (reps.length()>=4){Toast.makeText(PlaningActivity.this,"number of reps must be lower than 1000",Toast.LENGTH_LONG).show();}
                else{
                    exercises.add(new Exercise(name,Integer.parseInt(reps),rest));
                    ReDrawLayout();
                }

            }
        });

        backButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_buttonID ){
            finish();
        }
        else if (id == R.id.save_buttonID ){
            get_CheckBoxes();
            String name = workoutName.getText().toString();
            ArrayList<Exercise> ex = exercises;
            WorkoutPlan workoutPlan = new WorkoutPlan(name, ex, days);

            if(name.length()==0){Toast.makeText(PlaningActivity.this,"you must name your training",Toast.LENGTH_LONG).show();}
            else if (extras!= null){
                int index = (extras.getInt("index"));
                ((MyApplication) this.getApplication()).setIndexAllPlan(workoutPlan,index);
                Toast.makeText(PlaningActivity.this,"Saved",Toast.LENGTH_LONG).show();
                MyApplication.adapter.notifyItemChanged(index);
                finish();
            }
            else {
                ((MyApplication) this.getApplication()).addAllPlan(workoutPlan);
                Toast.makeText(PlaningActivity.this,"Saved",Toast.LENGTH_LONG).show();
                finish();
            }
            ((MyApplication)this.getApplication()).saveData();
        }
        else {
            for (int j = 0; j < exercises.size(); j++) {
                if (id == D_BT.get(j).getId()) {
                    exercises.remove(j);
                    ReDrawLayout();
                    break;
                }
                if(id == E_BT.get(j).getId()){
                    createPopUpDialog(j);
                    break;
                }
            }
        }
    }

    public void ReDrawLayout(){

        relativeLayout_for_texts.removeAllViews();
        relativeLayout_for_bottoms.removeAllViews();

        TV.clear();
        D_BT.clear();
        E_BT.clear();

        int plusRow =0;
        for (int i =0; i<exercises.size(); i++){

            ScrollView scrollView = new ScrollView(PlaningActivity.this);
            ScrollView.LayoutParams paramsScroll = new ScrollView.LayoutParams
                    (ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.WRAP_CONTENT);
            paramsScroll.bottomMargin = 100;
            scrollView.setLayoutParams(paramsScroll);

            TV.add(new TextView(PlaningActivity.this));
            RelativeLayout.LayoutParams paramsTV = new RelativeLayout.LayoutParams
                    (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            D_BT.add(new Button(PlaningActivity.this));
            RelativeLayout.LayoutParams paramsD_BT=new RelativeLayout.LayoutParams
                    (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            E_BT.add(new Button(PlaningActivity.this));
            RelativeLayout.LayoutParams paramsE_BT=new RelativeLayout.LayoutParams
                    (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            String PrevText;
            if (i!=0){
                PrevText = exercises.get(i-1).getName() + "\t" + exercises.get(i-1).getReps_number() + "\t" + exercises.get(i-1).getRest_time();
            }else PrevText = "";

            final int rowLength = 25;
            plusRow += (int)Math.ceil(PrevText.length()/rowLength);
            int space = (i+plusRow)*120;

            paramsTV.leftMargin = 70;    paramsD_BT.leftMargin = 0;    paramsE_BT.leftMargin = 150;
            paramsTV.topMargin  = space; paramsD_BT.topMargin = space; paramsE_BT.topMargin = space;

            String CurText = exercises.get(i).getName() + "\t" + exercises.get(i).getReps_number() + "\t" + exercises.get(i).getRest_time();
            TV.get(i).setText(CurText);
            setTextViewLook(TV.get(i));
            TV.get(i).setLayoutParams(paramsTV);


            D_BT.get(i).setId(i);
            D_BT.get(i).setOnClickListener(this);
            D_BT.get(i).setText(R.string.delete);
            setButtonLook(D_BT.get(i));
            D_BT.get(i).setLayoutParams(paramsD_BT);

            E_BT.get(i).setId(exercises.size()+i);
            E_BT.get(i).setOnClickListener(this);
            E_BT.get(i).setText(R.string.edit);
            setButtonLook(E_BT.get(i));
            E_BT.get(i).setLayoutParams(paramsE_BT);

            scrollView.addView(TV.get(i));
            relativeLayout_for_texts.addView(scrollView);
            relativeLayout_for_bottoms.addView(D_BT.get(i));
            relativeLayout_for_bottoms.addView(E_BT.get(i));
        }
    }

    private void set_checkBoxes(boolean[] days) {
        cb_1.setChecked(days[0]);
        cb_2.setChecked(days[1]);
        cb_3.setChecked(days[2]);
        cb_4.setChecked(days[3]);
        cb_5.setChecked(days[4]);
        cb_6.setChecked(days[5]);
        cb_7.setChecked(days[6]);
    }

    private void get_CheckBoxes() {
        days[0] = cb_1.isChecked();
        days[1] = cb_2.isChecked();
        days[2] = cb_3.isChecked();
        days[3] = cb_4.isChecked();
        days[4] = cb_5.isChecked();
        days[5] = cb_6.isChecked();
        days[6] = cb_7.isChecked();
    }

    public void setTextViewLook(TextView t){
        t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        t.setTextSize(20);
        t.setPadding(20, 50, 20, 50);
    }
    public void setButtonLook(Button b){
        b.setPadding(20, 50, 20, 50);
        b.setWidth(10);
        b.setHeight(150);
    }

    private void createPopUpDialog(int ex_index){

        Exercise currentE = exercises.get(ex_index) ;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.exercise_set);
        EditText setName =  dialog.findViewById(R.id.setNameID);
        EditText setReps =  dialog.findViewById(R.id.setRepsID);
        Button setButton =  dialog.findViewById(R.id.setButtonID);
        NumberPicker minutesPicker2 = dialog.findViewById(R.id.minute_pickerID);
        NumberPicker secondsPicker2 = dialog.findViewById(R.id.secund_pickerID);

        int a =60;
        String[] data = new String[a];
        for (int i =0; i<a; i++){
            data[i] = String.valueOf(i);
        }
        minutesPicker2.setMinValue(0);
        minutesPicker2.setMaxValue(data.length-1);
        minutesPicker2.setDisplayedValues(data);
        secondsPicker2.setMinValue(0);
        secondsPicker2.setMaxValue(data.length-1);
        secondsPicker2.setDisplayedValues(data);


        setName.setText(currentE.getName());
        setReps.setText(String.valueOf(currentE.getReps_number()));
        String timeString = currentE.getRest_time();
        int D = timeString.indexOf(":");
        int minutes = Integer.parseInt(timeString.substring(0,D));
        int seconds = Integer.parseInt(timeString.substring(D+1));
        minutesPicker2.setValue(minutes);
        secondsPicker2.setValue(seconds);


        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = setName.getText().toString();
                String reps = setReps.getText().toString();
                String rest = minutesPicker2.getValue()+":"+secondsPicker2.getValue();

                if      (name.length()==0){Toast.makeText(PlaningActivity.this,"you must name your exercise",Toast.LENGTH_LONG).show();}
                else if (reps.length()==0){Toast.makeText(PlaningActivity.this,"Please enter the number of reps",Toast.LENGTH_LONG).show();}
                else if (reps.length()==0){Toast.makeText(PlaningActivity.this,"Please enter the number of reps",Toast.LENGTH_LONG).show();}
                else if (name.length()>50){Toast.makeText(PlaningActivity.this,"too much character for an exercise name",Toast.LENGTH_LONG).show();}
                else if (reps.length()>=4){Toast.makeText(PlaningActivity.this,"number of reps must be lower than 1000",Toast.LENGTH_LONG).show();}
                else{
                    Exercise editedE = new Exercise(name,Integer.parseInt(reps),rest);
                    exercises.set(ex_index,editedE);
                    ReDrawLayout();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }
    @Override
    public void onBackPressed() {

    }
}

