package com.example.mikey.maps.Trails;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Trooper on 12/8/2016.
 */

public class TrailHistory {
    private String trailName;
    private Date date;
    private Time duration;
    private int Steps;


    public TrailHistory(String trailName, Date date, Time duration, int steps) {

        this.trailName = trailName;
        this.date = date;
        this.duration = duration;
        Steps = steps;
    }


    public String getTrailName() {
        return trailName;
    }

    public Date getDate() {
        return date;
    }

    public Time getDuration() {
        return duration;
    }

    public int getSteps() {
        return Steps;
    }


    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setSteps(int steps) {
        Steps = steps;
    }
}
