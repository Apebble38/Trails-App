package com.example.mikey.maps.Trails;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Trooper on 12/8/2016.
 */

public class TrailHistory {
    private String trailName;
    private String date;
    private long duration;
    private int Steps;


    public TrailHistory(String trailName, String date, long duration, int steps) {

        this.trailName = trailName;
        this.date = date;
        this.duration = duration;
        Steps = steps;
    }


    public String getTrailName() {
        return trailName;
    }

    public String getDate() {
        return date;
    }

    public long getDuration() {
        return duration;
    }

    public int getSteps() {
        return Steps;
    }


    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setSteps(int steps) {
        Steps = steps;
    }
}
