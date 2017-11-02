package com.kaarvik.lvysaurworkouttracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Set;
import com.kaarvik.lvysaurworkouttracker.utils.PlateCalculator;
import com.kaarvik.lvysaurworkouttracker.views.RepsNumberButton;

import org.w3c.dom.Text;

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
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnFocusChangeListener {

        public View mView;
        public RealmList<Set> sets;

        public TextView textViewSetWeight;
        public EditText editTextSetWeight;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            textViewSetWeight = (TextView) view.findViewById(R.id.text_exercise_weight);
            textViewSetWeight.setOnClickListener(this);

            editTextSetWeight = (EditText) view.findViewById(R.id.edit_exercise_weight);
            editTextSetWeight.setOnFocusChangeListener(this);
        }

        @Override
        public void onClick(View view) {
            //Click handler for all registered elements in the exercise view

            if (view.getId() == textViewSetWeight.getId()){
                //Set weight text pressed, change to edit mode
                textViewSetWeight.setVisibility(TextView.GONE);
                editTextSetWeight.setVisibility(EditText.VISIBLE);

                editTextSetWeight.setText(Double.toString(sets.get(0).getWeight()));
                editTextSetWeight.requestFocus();

                //Open soft keyboard
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextSetWeight, InputMethodManager.SHOW_IMPLICIT);
            }
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            //Ignore focus events. Only care when focus is lost
            if (hasFocus) {
                return;
            }

            if (view.getId() == editTextSetWeight.getId()) {
                //Change to display mode. Update the exercise sets with the new weight
                textViewSetWeight.setVisibility(TextView.VISIBLE);
                editTextSetWeight.setVisibility(EditText.GONE);

                //Close the soft keyboard
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(textViewSetWeight.getWindowToken(), 0);

                //Handle editText update

            }
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
        holder.sets = mDataset.get(position).getSets();

        setupExerciseName(holder, position);
        setupSetList(holder, position);
        setupSetWeight(holder, position);
        setupPlateCalculator(holder, position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void setupExerciseName(ViewHolder holder, int position) {
        Exercise exercise = mDataset.get(position);
        String exerciseName = Exercise.getTypeText(holder.mView.getContext(), exercise.getType());

        TextView exerciseNameTextView = (TextView) holder.mView.findViewById(R.id.text_exercise_name);
        exerciseNameTextView.setText(exerciseName);
    }

    private void setupSetList(ViewHolder holder, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(holder.mView.getContext());
        LinearLayout setListLayout = (LinearLayout) holder.mView.findViewById(R.id.set_list);

        for(int i = 0; i < holder.sets.size(); i++){
            addSetView(layoutInflater, setListLayout, holder.sets.get(i));
        }
    }

    private void setupSetWeight(ViewHolder holder, int position) {
        double setWeight = holder.sets.get(0).getWeight();

        TextView textViewSetWeight = (TextView) holder.mView.findViewById(R.id.text_exercise_weight);
        textViewSetWeight.setText(holder.mView.getResources().getString(R.string.weight_lbs, setWeight));
    }

    private void setupPlateCalculator(ViewHolder holder, int position) {
        Exercise exercise = mDataset.get(position);
        double setWeight = holder.sets.get(0).getWeight();

        //If empty string, set the plate text to special string
        String plateCalculatorText = PlateCalculator.calculatePlates(setWeight, exercise.getType());
        if (plateCalculatorText.equals("")) {
            plateCalculatorText = holder.mView.getResources().getString(R.string.no_added_weight);
        }
        TextView textViewPlateCalculator = (TextView) holder.mView.findViewById(R.id.text_plate_calculator);
        textViewPlateCalculator.setText(plateCalculatorText);
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
}
