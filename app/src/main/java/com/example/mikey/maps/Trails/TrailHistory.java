package com.example.mikey.maps.Trails;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Trooper on 12/8/2016.
 */

public class TrailHistory implements Parcelable {
    private String trailName;
    private String date;
    private long duration;
    private int steps;


    public TrailHistory(String trailName, String date, long duration, int steps) {

        this.trailName = trailName;
        this.date = date;
        this.duration = duration;
        this.steps = steps;
    }

    public TrailHistory(Parcel in){
        this.trailName = in.readString();
        this.date = in.readString();
        this.duration = in.readLong();
        this.steps = in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.trailName);
        dest.writeString(this.date);
        dest.writeLong(this.duration);
        dest.writeInt(this.steps);
    }


    public static final Parcelable.Creator<TrailHistory> CREATOR = new Parcelable.Creator<TrailHistory>() {

        public TrailHistory createFromParcel(Parcel in) {
            return new TrailHistory(in);
        }

        public TrailHistory[] newArray(int size) {
            return new TrailHistory[size];
        }
    };


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
        return steps;
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
        steps = steps;
    }
}
