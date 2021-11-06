package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.my_workout_app.R;
import java.util.ArrayList;

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
        Boolean[] days = item.getDays_bool();
        for (int i =0; i<7; i++){
            if(days[i])SB.append(days_name[i]+" ");
        }

        holder.days.setText(SB);
        holder.numberOfEx.setText("Number of Exercise: "+item.getExercises().size());
    }

    @Override
    public int getItemCount() {
        return workoutPlans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView days;
        public TextView numberOfEx;
        public ViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

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
