package com.kaarvik.lvysaurworkouttracker.adapters;

import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Set;
import com.kaarvik.lvysaurworkouttracker.utils.ExerciseType;
import com.kaarvik.lvysaurworkouttracker.views.RepsNumberButton;

import io.realm.RealmList;

/**
 * Created by zkaar on 2017-09-19.
 */

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ViewHolder> {

    private RealmList<Exercise> mDataset;
    private ListView mListView;
    private Adapter mAdapter;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public RealmList<Set> sets;
        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExerciseListAdapter(RealmList<Exercise> exercises) {
        mDataset = exercises;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExerciseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_single_exercise, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = mDataset.get(position);
        String exerciseName = ExerciseType.getText(holder.mView.getContext(), exercise.getType());

        TextView textView = (TextView) holder.mView.findViewById(R.id.text_exercise_name);
        textView.setText(exerciseName);

        //Setup set list
        holder.sets = mDataset.get(position).getSets();
        LayoutInflater layoutInflater = LayoutInflater.from(holder.mView.getContext());
        LinearLayout setListLayout = (LinearLayout) holder.mView.findViewById(R.id.set_list);

        for(int i = 0; i < holder.sets.size(); i++){
            addSetView(layoutInflater, setListLayout, holder.sets.get(i));
        }
    }

    private View addSetView(LayoutInflater layoutInflater, LinearLayout setListLayout, final Set set) {
        View setView = layoutInflater.inflate(R.layout.fragment_single_set, setListLayout, false);
        setListLayout.addView(setView);

        final RepsNumberButton button = (RepsNumberButton) setView.findViewById(R.id.button_set_reps);
        button.setNumber(String.valueOf(set.getGoalReps()));
        button.setOnClickListener(new RepsNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(button.getNumber());
                //set.setCompletedReps(num);
                if( num >= set.getGoalReps()) {
                    button.setBackgroundColor(view.getResources().getColor(R.color.set_button_success));
                } else {
                    button.setBackgroundColor(view.getResources().getColor(R.color.set_button_warning));
                }
            }
        });

        return setView;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
