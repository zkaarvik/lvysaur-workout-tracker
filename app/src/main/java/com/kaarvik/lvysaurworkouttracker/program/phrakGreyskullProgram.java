package com.kaarvik.lvysaurworkouttracker.program;

import com.kaarvik.lvysaurworkouttracker.data.Workout;
import com.kaarvik.lvysaurworkouttracker.interfaces.WorkoutProgram;
import com.kaarvik.lvysaurworkouttracker.utils.DataProvider;

import io.realm.Realm;

/**
 * Created by Zach on 2/20/2017.
 */

public class PhrakGreyskullProgram implements WorkoutProgram {

    @Override
    public Workout getNextWorkout(Realm realm, Workout lastWorkout) {
        if (lastWorkout == null) {
            return getInitialWorkout(realm);
        } else {

        }
        return null;
    }

    public Workout getInitialWorkout(Realm realm) {
        return DataProvider.createNewWorkout(realm);
    }


}
