package com.kaarvik.lvysaurworkouttracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.adapters.ExerciseListAdapter;
import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Workout;
import com.kaarvik.lvysaurworkouttracker.program.WorkoutProgram;
import com.kaarvik.lvysaurworkouttracker.program.PhrakGreyskullProgram;
import com.kaarvik.lvysaurworkouttracker.utils.DataProvider;

import io.realm.Realm;

import static com.kaarvik.lvysaurworkouttracker.utils.Constants.EXTRA_WORKOUTID;


public class WorkoutActivity extends AppCompatActivity {

    private Realm realm;
    private Workout workout;
    private WorkoutProgram workoutProgram;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //Get realm instance
        realm = Realm.getDefaultInstance();

        //Get the workout program we are using
        getWorkoutProgram(realm);

        //Enable up button in the toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Receive the intent and get any passed workout id
        Intent intent = getIntent();
        long workoutId = intent.getLongExtra(EXTRA_WORKOUTID, -1);
        loadWorkout(workoutId);

        //Setup the exercise list - recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.exercise_recycler_view);
        //use linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //Specify adapter
        mAdapter = new ExerciseListAdapter(workout.getExercises());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void getWorkoutProgram(Realm realm) {
        //Todo: Handle this elsewhere, should be able to swap program easily
        workoutProgram = new PhrakGreyskullProgram();
    }

    private void loadWorkout(long workoutId) {
        //If valid workoutId was passed, a workout was selected to be loaded.
        //Otherwise, we need to create a new (the next) workout
        if (workoutId == -1) {
            //Create a new workout
            //Todo: Get previous workout
            Workout lastWorkout = null;
            workout = workoutProgram.getNextWorkout(lastWorkout);
            workout = DataProvider.saveWorkout(realm, workout);

        } else {
            //Workout already exists
            workout = DataProvider.getWorkout(realm, workoutId);
        }
    }
}
