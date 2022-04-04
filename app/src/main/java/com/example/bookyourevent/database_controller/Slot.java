package com.example.bookyourevent.database_controller;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Time;

public class Slot implements Serializable, Parcelable {
    String name;
    Time startTime,endTime;
    int price;
    public Slot()
    {

    }
    public Slot(String name,Time startTime,Time endTime,int price)
    {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    protected Slot(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Slot> CREATOR = new Creator<Slot>() {
        @Override
        public Slot createFromParcel(Parcel in) {
            return new Slot(in);
        }

        @Override
        public Slot[] newArray(int size) {
            return new Slot[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
