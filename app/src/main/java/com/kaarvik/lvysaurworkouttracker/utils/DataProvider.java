package com.kaarvik.lvysaurworkouttracker.utils;

import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Workout;

import java.util.Date;

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

    public static Workout createNewWorkout(Realm realm, String type, Date date, double bodyWeight) {
        realm.beginTransaction();

        Number currentMaxId = realm.where(Workout.class).max("id");
        long newId = getNextId(currentMaxId);

        Workout newWorkout = realm.createObject(Workout.class, newId);
        newWorkout.setType(type);
        newWorkout.setDate(date);
        newWorkout.setBodyWeight(bodyWeight);

        realm.commitTransaction();

        return newWorkout;
    }

    public static Exercise createNewExcercise(Realm realm, String type) {
        realm.beginTransaction();

        Number currentMaxId = realm.where(Exercise.class).max("id");
        long newId = getNextId(currentMaxId);

        Exercise newExercise = realm.createObject(Exercise.class, newId);
        newExercise.setType(type);

        realm.commitTransaction();

        return newExercise;
    }

    private static long getNextId(Number lastId) {
        long newId;
        if(lastId == null) {
            newId = 1;
        } else {
            newId = lastId.longValue() + 1;
        }
        return newId;
    }
}
