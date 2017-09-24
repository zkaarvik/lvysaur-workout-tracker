package com.kaarvik.lvysaurworkouttracker.data;

import android.content.Context;

import com.kaarvik.lvysaurworkouttracker.R;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Zach on 1/7/2017.
 */

public class Exercise extends RealmObject {

    @PrimaryKey
    private long id;
    private String type;
    private RealmList<Set> sets;
    private RealmList<Set> warmups;

    public final static String TYPE_SQUAT = "TYPE_SQUAT";
    public final static String TYPE_DEADLIFT = "TYPE_DEADLIFT";
    public final static String TYPE_OVERHEADPRESS = "TYPE_OVERHEADPRESS";
    public final static String TYPE_BENCHPRESS = "TYPE_BENCHPRESS";
    public final static String TYPE_CHINUPS = "TYPE_CHINUPS";
    public final static String TYPE_BARBELLROWS = "TYPE_BARBELLROWS";

    public Exercise() {
        sets = new RealmList<>();
        warmups = new RealmList<>();
    }

    public void addSet(Set set) {
        sets.add(set);
    }

    public void addWarmup(Set warmup) {
        warmups.add(warmup);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RealmList<Set> getSets() {
        return sets;
    }

    public void setSets(RealmList<Set> sets) {
        this.sets = sets;
    }

    public RealmList<Set> getWarmups() {
        return warmups;
    }

    public void setWarmups(RealmList<Set> warmups) {
        this.warmups = warmups;
    }

    public static String getTypeText(Context context, String exerciseType) {
        switch (exerciseType) {
            case TYPE_SQUAT:
                return context.getString(R.string.exercise_TYPE_BARBELLROWS);
            case TYPE_DEADLIFT:
                return context.getString(R.string.exercise_TYPE_DEADLIFT);
            case TYPE_OVERHEADPRESS:
                return context.getString(R.string.exercise_TYPE_OVERHEADPRESS);
            case TYPE_BENCHPRESS:
                return context.getString(R.string.exercise_TYPE_BENCHPRESS);
            case TYPE_CHINUPS:
                return context.getString(R.string.exercise_TYPE_CHINUPS);
            case TYPE_BARBELLROWS:
                return context.getString(R.string.exercise_TYPE_BARBELLROWS);
            default:
                return "undefined exercise";
        }
    }

    public static boolean isUsingBarbell(String exerciseType) {
        //Only exercise that doesn't use a barbell is chinups currently
        return !(exerciseType.equals(TYPE_CHINUPS));
    }
}
