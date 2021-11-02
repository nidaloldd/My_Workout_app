package com.example.my_workout_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import myClasses.Exercise;
import myClasses.Exercise_Plan;

public class PlaningActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView outputText;
    private EditText addName;
    private EditText addReps;
    private EditText addRest;
    private Button addExerciseButton;
    private Button backButton;
    private ArrayList<TextView> TV ;
    private ArrayList<Button> D_BT ;
    private ArrayList<Button> E_BT ;
    private RelativeLayout relativeLayout_for_texts;
    private RelativeLayout relativeLayout_for_bottoms;
    private CheckBox cb_1;
    private CheckBox cb_2;
    private CheckBox cb_3;
    private CheckBox cb_4;
    private CheckBox cb_5;
    private CheckBox cb_6;
    private CheckBox cb_7;

    private Exercise_Plan exercise_plan;
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planing);
        addName = findViewById(R.id.add_nameID);
        addReps = findViewById(R.id.add_repsID);
        addRest = findViewById(R.id.add_restID);
        addExerciseButton = findViewById(R.id.add_exercise_buttonID);
        TV = new ArrayList<TextView>();
        D_BT = new ArrayList<Button>();
        E_BT = new ArrayList<Button>();
        backButton = findViewById(R.id.back_buttonID);
        relativeLayout_for_texts = (RelativeLayout) findViewById(R.id.RL_for_textsID);
        relativeLayout_for_bottoms = (RelativeLayout) findViewById(R.id.RL_for_ButtomsID);

        cb_1 = findViewById(R.id.cb_1_ID);
        cb_2 = findViewById(R.id.cb_2_ID);
        cb_3 = findViewById(R.id.cb_3_ID);
        cb_4 = findViewById(R.id.cb_4_ID);
        cb_5 = findViewById(R.id.cb_5_ID);
        cb_6 = findViewById(R.id.cb_6_ID);
        cb_7 = findViewById(R.id.cb_7_ID);


        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString();
                int reps = Integer.parseInt(addReps.getText().toString());
                int rest = Integer.parseInt(addRest.getText().toString());
                exercises.add(new Exercise(name,reps,rest));
                RedrawLayout();
               // Clear_EditTexts();
            }
        });

        backButton.setOnClickListener(this::onClick);

    }

    public void Clear_EditTexts (){
        addName.setText("");
        addReps.setText("");
        addRest.setText("");
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.back_buttonID ){
            Intent return_intent = getIntent();
            setResult(RESULT_OK, return_intent);
            finish();
        }
        else {
            for (int j = 0; j < exercises.size(); j++) {
                if (i == D_BT.get(j).getId()) {
                    exercises.remove(j);
                    RedrawLayout();
                    break;
                }
            }
        }
    }

    public void RedrawLayout(){

        relativeLayout_for_texts.removeAllViews();
        relativeLayout_for_bottoms.removeAllViews();

        TV.clear();
        D_BT.clear();
        E_BT.clear();

        for (int i =0; i<exercises.size(); i++){
            TV.add(new TextView(PlaningActivity.this));
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            D_BT.add(new Button(PlaningActivity.this));
            RelativeLayout.LayoutParams params2=new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            E_BT.add(new Button(PlaningActivity.this));
            RelativeLayout.LayoutParams params3=new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);

            params.leftMargin = 70;    params2.leftMargin = 0;   params3.leftMargin = 150;
            params.topMargin  = i*120; params2.topMargin  = i*120; params3.topMargin  = i*120;

            String text = exercises.get(i).getName()+"\t"+exercises.get(i).getReps_number()+"\t"+exercises.get(i).getRest_time();
            TV.get(i).setText(text);
            setTextViewLook(TV.get(i));
            TV.get(i).setLayoutParams(params);

            D_BT.get(i).setId(i);
            D_BT.get(i).setOnClickListener(this::onClick);
            D_BT.get(i).setText(R.string.delete);
            setButtonLook(D_BT.get(i));
            D_BT.get(i).setLayoutParams(params2);

            E_BT.get(i).setId(exercises.size()+i);
            E_BT.get(i).setOnClickListener(this::onClick);
            E_BT.get(i).setText(R.string.edit);
            setButtonLook(E_BT.get(i));
            E_BT.get(i).setLayoutParams(params3);

            relativeLayout_for_texts.addView(TV.get(i));
            relativeLayout_for_bottoms.addView(D_BT.get(i));
            relativeLayout_for_bottoms.addView(E_BT.get(i));
        }
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
}