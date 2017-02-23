package com.kaarvik.lvysaurworkouttracker.program;

import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Workout;
import com.kaarvik.lvysaurworkouttracker.utils.DataProvider;

import java.util.Date;

import io.realm.Realm;

/**
 *  A Workout Program controls the structure of the workouts for a given program.
 *      The implementation class will create new workouts based on the previous workout
 */

public abstract class WorkoutProgram {

    private Realm realm;

    abstract public Workout getNextWorkout(Workout lastWorkout);

    protected WorkoutProgram(Realm realm) {
        this.realm = realm;
    }

    protected Workout buildWorkout(String type, double bodyweight) {
        Workout newWorkout = DataProvider.createNewWorkout(realm, type, new Date(), bodyweight);
        return newWorkout;
    }

    protected Exercise buildExcercise(String type, int numSets, double weight) {
        Exercise newExercise = DataProvider.createNewExcercise(realm, type);
        return newExercise;
    }
}
