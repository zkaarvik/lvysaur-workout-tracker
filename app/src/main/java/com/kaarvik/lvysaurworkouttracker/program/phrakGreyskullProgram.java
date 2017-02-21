package com.kaarvik.lvysaurworkouttracker.program;

import com.kaarvik.lvysaurworkouttracker.data.Workout;
import com.kaarvik.lvysaurworkouttracker.interfaces.WorkoutProgram;

/**
 * Created by Zach on 2/20/2017.
 */

public class PhrakGreyskullProgram implements WorkoutProgram {
    @Override
    public Workout getInitialWorkout() {
        return null;
    }

    @Override
    public Workout getNextWorkout(Workout lastWorkout) {
        return null;
    }
}
