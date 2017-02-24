package com.kaarvik.lvysaurworkouttracker.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Zach on 1/7/2017.
 */

public class Set extends RealmObject {
    @PrimaryKey
    private long id;
    private int goalReps;
    private int completedReps;
    private double weight;
    private boolean isAmrap;

    public boolean isAmrap() {
        return isAmrap;
    }

    public void setAmrap(boolean amrap) {
        isAmrap = amrap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGoalReps() {
        return goalReps;
    }

    public void setGoalReps(int goalReps) {
        this.goalReps = goalReps;
    }

    public int getCompletedReps() {
        return completedReps;
    }

    public void setCompletedReps(int completedReps) {
        this.completedReps = completedReps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
