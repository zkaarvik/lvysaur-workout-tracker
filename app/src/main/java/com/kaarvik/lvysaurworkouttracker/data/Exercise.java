package com.kaarvik.lvysaurworkouttracker.data;

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
}
