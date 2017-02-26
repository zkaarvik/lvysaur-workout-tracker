package com.kaarvik.lvysaurworkouttracker.program;

import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Workout;
import com.kaarvik.lvysaurworkouttracker.utils.DataProvider;
import com.kaarvik.lvysaurworkouttracker.utils.ExerciseType;

import java.util.Date;

import io.realm.Realm;

/*
 * Program defined here: http://i.stack.imgur.com/OUcEY.png
 * Four possible workout structures
 * For all workouts in Phrak's program:
  * Last set is AMRAP
  * If final AMRAP set has 10+ reps, double weight increase next time
  * If final AMRAP set has less than 5 reps, deload next time by 10%
  * Standard increment is 2.5lbs for upper body, 5lbs for lower body
 */

public class PhrakGreyskullProgram extends WorkoutProgram {

    public static final String TYPE_D1T1 = "D1T1";
    public static final String TYPE_D1T2 = "D1T2";
    public static final String TYPE_D2T1 = "D2T1";
    public static final String TYPE_D2T2 = "D2T2";

    @Override
    public Workout getNextWorkout(Workout lastWorkout) {
        if (lastWorkout == null || lastWorkout.getType() == TYPE_D1T1) {
            return buildD1T1Workout();
        } else {

        }
        return null;
    }

    /*
     * D1T1 Workout for Phrak's Greyskull:
     * 3x5 Overhead Press
     * 3x5 Chinups
     * 3x5 Squats
     */
    private Workout buildD1T1Workout() {
        Workout newWorkout = buildWorkout(TYPE_D1T1, -1);
        
        Exercise overheadPress = buildExcercise(ExerciseType.TYPE_OVERHEADPRESS, 3, 5, 45);
        Exercise chinups = buildExcercise(ExerciseType.TYPE_CHINUPS, 3, 5, 0);
        Exercise squats = buildExcercise(ExerciseType.TYPE_SQUAT, 3, 5, 45);

        //Mark last sets as AMRAP
        overheadPress.getSets().get(2).setAmrap(true);
        chinups.getSets().get(2).setAmrap(true);
        squats.getSets().get(2).setAmrap(true);

        newWorkout.addExercise(overheadPress);
        newWorkout.addExercise(chinups);
        newWorkout.addExercise(squats);
        
        return newWorkout;
    }

    /*
     * D1T2 Workout for Phrak's Greyskull:
     * 3x5 Bench Press
     * 3x5 Barbell Rows
     * 3x5 Squats
     */
    private Workout buildD1T2Workout() {
        Workout initialWorkout = buildWorkout(TYPE_D1T2, -1);
        return initialWorkout;
    }

    /*
     * D2T1 Workout for Phrak's Greyskull:
     * 3x5 Overhead Press
     * 3x5 Chinups
     * 3x5 Deadlifts
     */
    private Workout buildD2T1Workout() {
        Workout initialWorkout = buildWorkout(TYPE_D2T1, -1);
        return initialWorkout;
    }

    /*
     * D2T2 Workout for Phrak's Greyskull:
     * 3x5 Bench Press
     * 3x5 Barbell Rows
     * 3x5 Deadlifts
     */
    private Workout buildD2T2Workout() {
        Workout initialWorkout = buildWorkout(TYPE_D2T2, -1);
        return initialWorkout;
    }


}
