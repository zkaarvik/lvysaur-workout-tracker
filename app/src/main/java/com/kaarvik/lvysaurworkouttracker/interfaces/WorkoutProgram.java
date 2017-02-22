package com.kaarvik.lvysaurworkouttracker.interfaces;

import com.kaarvik.lvysaurworkouttracker.data.Workout;

import io.realm.Realm;

/**
 *  A WorkoutProgram controls the structure of the workouts for a given program.
 *      The implementation class will create new workouts based on the previous workout
 */

public interface WorkoutProgram {

    public Workout getNextWorkout(Realm realm, Workout lastWorkout);
}
