package com.kaarvik.lvysaurworkouttracker.adapters;

import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.utils.ExerciseType;

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
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = mDataset.get(position);
        String exerciseName = ExerciseType.getText(holder.mView.getContext(), exercise.getType());

        TextView textView = (TextView) holder.mView.findViewById(R.id.test_text);
        textView.setText(exerciseName);

        //Setup the set list
        mListView = (ListView) holder.mView.findViewById(R.id.set_list_view);
        mAdapter = new SetListAdapter(mDataset.get(position).getSets());
        mListView.setAdapter((ListAdapter) mAdapter);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
