package com.kaarvik.lvysaurworkouttracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.data.Workout;
import com.kaarvik.lvysaurworkouttracker.utils.DataProvider;

import io.realm.Realm;

import static com.kaarvik.lvysaurworkouttracker.utils.Constants.EXTRA_WORKOUTID;


public class WorkoutActivity extends AppCompatActivity {

    private Realm realm;
    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //Get realm instance
        realm = Realm.getDefaultInstance();

        //Receive the intent and get any passed workout id
        Intent intent = getIntent();
        long workoutId = intent.getLongExtra(EXTRA_WORKOUTID, -1);
        loadWorkout(workoutId);

        //Enable up button in the toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void loadWorkout(long workoutId) {
        if (workoutId == -1) {
            //Create a new workout
        } else {
            //Workout already exists
            workout = DataProvider.getWorkout(realm, workoutId);
        }
    }
}
