package com.kaarvik.lvysaurworkouttracker.program;

import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Set;
import com.kaarvik.lvysaurworkouttracker.data.Workout;

import java.util.Date;

/**
 *  A Workout Program controls the structure of the workouts for a given program.
 *      The implementation class will create new workouts based on the previous workout
 */

public abstract class WorkoutProgram {

    abstract public Workout getNextWorkout(Workout lastWorkout);

    protected Workout buildWorkout(String type, double bodyweight) {
        Workout newWorkout = new Workout();
        newWorkout.setType(type);
        newWorkout.setBodyWeight(bodyweight);
        newWorkout.setDate(new Date());

        return newWorkout;
    }

    protected Exercise buildExcercise(String type, int numSets, int numReps, double weight) {
        Exercise newExercise = new Exercise();
        newExercise.setType(type);

        for (int i = 0; i < numSets; i++) {
            newExercise.addSet(buildSet(numReps, weight));
        }

        return newExercise;
    }

    protected Set buildSet(int goalReps, double weight) {
        Set newSet = new Set();
        newSet.setGoalReps(goalReps);
        newSet.setWeight(weight);
        newSet.setCompletedReps(-1); //-1 reps indicates set not performed yet, 0 reps indicates no reps could be completed (set performed)
        newSet.setAmrap(false); //By default a set is not AMRAP, specific program can modify individual sets

        return newSet;
    }
}
