package com.kaarvik.lvysaurworkouttracker.data;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Zach on 1/7/2017.
 */

public class Workout extends RealmObject {
    @PrimaryKey
    private long id;
    private String type;
    private Date date;
    private double bodyWeight;
    private RealmList<Exercise> exercises;

    public Workout() {
        exercises = new RealmList<>();
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public RealmList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<Exercise> exercises) {
        this.exercises = exercises;
    }
}
