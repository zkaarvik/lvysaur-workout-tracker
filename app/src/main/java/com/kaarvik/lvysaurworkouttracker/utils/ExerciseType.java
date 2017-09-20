package com.kaarvik.lvysaurworkouttracker.utils;

import android.content.Context;

import com.kaarvik.lvysaurworkouttracker.R;

/**
 * Defines possible exercises
 */

public final class ExerciseType {
    public final static String TYPE_SQUAT = "TYPE_SQUAT";
    public final static String TYPE_DEADLIFT = "TYPE_DEADLIFT";
    public final static String TYPE_OVERHEADPRESS = "TYPE_OVERHEADPRESS";
    public final static String TYPE_BENCHPRESS = "TYPE_BENCHPRESS";
    public final static String TYPE_CHINUPS = "TYPE_CHINUPS";
    public final static String TYPE_BARBELLROWS = "TYPE_BARBELLROWS";

    public static String getText(Context context, String exerciseType) {
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
}
