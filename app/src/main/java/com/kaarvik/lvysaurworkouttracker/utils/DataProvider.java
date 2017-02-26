package com.kaarvik.lvysaurworkouttracker.utils;

import com.kaarvik.lvysaurworkouttracker.data.Exercise;
import com.kaarvik.lvysaurworkouttracker.data.Set;
import com.kaarvik.lvysaurworkouttracker.data.Workout;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
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

    public static Workout saveWorkout(Realm realm, Workout workout) {
        //We need to give the workout and its components ids if the workout is not managed by realm yet

        if (!workout.isManaged()) {
            setWorkoutIds(realm, workout);
        }

        realm.beginTransaction();
        Workout savedWorkout = realm.copyToRealmOrUpdate(workout);
        realm.commitTransaction();

        return savedWorkout;
    }

    private static long getNextId(Realm realm, Class clazz) {
        Number lastId = realm.where(clazz).max("id");

        if(lastId == null) {
            return 1;
        } else {
            return lastId.longValue() + 1;
        }
    }

    private static void setWorkoutIds(Realm realm, Workout workout) {
        int i = 0, j = 0;

        //Set sequential ids for workouts, exercises, sets, warmups
        workout.setId(getNextId(realm, Workout.class));

        //For each exercise, set id and iterate sets and warmups
        long nextExerciseId = getNextId(realm, Exercise.class);
        long nextSetId = getNextId(realm, Set.class);

        for (Exercise exercise : workout.getExercises()) {
            exercise.setId(nextExerciseId + i);

            //Set ids for work sets
            for (Set set : exercise.getSets()) {
                set.setId(nextSetId + j);
                j++;
            }

            //Set ids for warmup sets
            for (Set warmup : exercise.getWarmups()) {
                warmup.setId(nextSetId + j);
                j++;
            }

            i++;
        }
    }
}
