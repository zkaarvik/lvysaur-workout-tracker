package com.kaarvik.lvysaurworkouttracker.utils;

import com.kaarvik.lvysaurworkouttracker.data.Workout;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Zach on 2/20/2017.
 */

public final class DataProvider {

    public static Workout getWorkout(Realm realm, long workoutId) {
        RealmResults<Workout> results = realm.where(Workout.class).equalTo("id", workoutId).findAll();

        if (results.size() == 0) {
            //Did not find the workout that corresponds to the ID... error!
            return null;
        } else {
            return results.get(0);
        }
    }
}
