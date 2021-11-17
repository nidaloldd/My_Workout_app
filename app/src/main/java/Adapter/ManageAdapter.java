package Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_workout_app.DoWorkoutActivity;
import com.example.my_workout_app.ManageActivity;
import com.example.my_workout_app.PlaningActivity;
import com.example.my_workout_app.R;
import java.util.ArrayList;

import myClasses.Exercise;
import myClasses.WorkoutPlan;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.ViewHolder>{
    private Context context;
    private ArrayList<WorkoutPlan> workoutPlans;

    public  ManageAdapter(Context context, ArrayList<WorkoutPlan> workoutPlans){
        this.context = context;
        this.workoutPlans = workoutPlans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageAdapter.ViewHolder holder, int position) {

        WorkoutPlan item = workoutPlans.get(position);
        holder.name.setText(item.getName());
        StringBuilder SB = new StringBuilder();

        String[] days_name = new String[]{"Mon", "Tue", "Thu", "Wed", "Fri", "Sat","Sun"};
        boolean[] days = item.getDays_bool();
        for (int i =0; i<7; i++){
            if(days[i])SB.append(days_name[i]+" ");
        }

        holder.days.setText(SB);
        holder.numberOfEx.setText("Number of Exercise: "+item.getExercises().size());


        holder.deleteWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"delete",Toast.LENGTH_LONG).show();
                workoutPlans.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(),workoutPlans.size());
            }
        });

        holder.setWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"set",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,PlaningActivity.class);
                intent.putExtra("index",holder.getAdapterPosition());
                intent.putExtra("name",workoutPlans.get(holder.getAdapterPosition()).getName());
                intent.putExtra("days",workoutPlans.get(holder.getAdapterPosition()).getDays_bool());
                //intent.putExtra("exercises",workoutPlans.get(holder.getAdapterPosition()).getExercises());
                ArrayList<Exercise> ex = workoutPlans.get(holder.getAdapterPosition()).getExercises();

                ArrayList<String> ex_name = new ArrayList<String>();
                ArrayList<Integer>ex_reps = new ArrayList<Integer>();
                ArrayList<Integer>ex_rest = new ArrayList<Integer>();
                for(int i = 0 ; i< ex.size();i++){
                    ex_name.add(ex.get(i).getName());
                    ex_reps.add(ex.get(i).getReps_number());
                    ex_rest.add(ex.get(i).getRest_time());
                }
                intent.putExtra("ex_name",ex_name);
                intent.putExtra("ex_reps",ex_reps);
                intent.putExtra("ex_rest",ex_rest);

                //notifyItemChanged(holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
        holder.doWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"DO workout",Toast.LENGTH_LONG).show();

                Intent doWorkIntent = new Intent(context,DoWorkoutActivity.class);

                doWorkIntent.putExtra("index",holder.getAdapterPosition());
                doWorkIntent.putExtra("name",workoutPlans.get(holder.getAdapterPosition()).getName());
                doWorkIntent.putExtra("days",workoutPlans.get(holder.getAdapterPosition()).getDays_bool());
                ArrayList<Exercise> ex = workoutPlans.get(holder.getAdapterPosition()).getExercises();


                ArrayList<String> ex_name = new ArrayList<String>();
                ArrayList<Integer>ex_reps = new ArrayList<Integer>();
                ArrayList<Integer>ex_rest = new ArrayList<Integer>();
                for(int i = 0 ; i< ex.size();i++){
                    ex_name.add(ex.get(i).getName());
                    ex_reps.add(ex.get(i).getReps_number());
                    ex_rest.add(ex.get(i).getRest_time());
                }
                doWorkIntent.putExtra("ex_name",ex_name);
                doWorkIntent.putExtra("ex_reps",ex_reps);
                doWorkIntent.putExtra("ex_rest",ex_rest);

                context.startActivity(doWorkIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workoutPlans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Button setWorkout;
        public Button deleteWorkout;
        public Button doWorkout;
        public TextView name;
        public TextView days;
        public TextView numberOfEx;
        public ViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

            setWorkout = (Button) itemView.findViewById(R.id.setWorkoutButtonID);
            deleteWorkout = (Button) itemView.findViewById(R.id.deleteWorkoutButtonID);
            doWorkout = (Button) itemView.findViewById(R.id.doWorkoutButtonID);
            name = (TextView) itemView.findViewById(R.id.workoutNameTextID);
            days = (TextView) itemView.findViewById(R.id.daysTextID);
            numberOfEx = (TextView) itemView.findViewById(R.id.numberOfExID);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            WorkoutPlan item = workoutPlans.get(position);

            Toast.makeText(context,item.getName(),Toast.LENGTH_LONG).show();
        }
    }

}


