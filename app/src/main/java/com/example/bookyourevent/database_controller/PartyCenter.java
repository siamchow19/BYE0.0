package com.example.bookyourevent.database_controller;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PartyCenter implements Serializable, Parcelable {
    private String id,name,contactNumber,address,ownerId;
    private LatLng position;
    private int capacity;
    private Time startTime,endTime;
    private ArrayList<Slot> slots = new ArrayList<>();
    private Map<Date,Integer> reservationOnADate;
    public PartyCenter()
    {

    }
    public PartyCenter(String name,String contactNumber,String address,String ownerId,int capacity,LatLng position)
    {
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.ownerId = ownerId;
        this.position = position;
        this.capacity = capacity;
        reservationOnADate = new HashMap<>();
    }
    public PartyCenter(String name,String contactNumber,String address,String ownerId,int capacity,LatLng position,Map<Date,Integer> reservationOnADate,ArrayList<Slot> slots)
    {
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.ownerId = ownerId;
        this.position = position;
        this.capacity = capacity;
        this.reservationOnADate = reservationOnADate;
        this.slots = slots;
    }
    public PartyCenter(String id,String name,String contactNumber,String address,String ownerId)
    {
        this.id = id;
        this.address = address;
        this.contactNumber = contactNumber;
        this.position = new LatLng(10,20);
        Slot slot = new Slot("ABC",new Time(System.currentTimeMillis()),new Time(System.currentTimeMillis()),100);
        Slot slot1 = new Slot("ABC",new Time(System.currentTimeMillis()),new Time(System.currentTimeMillis()),100);
        this.slots.add(slot);
        this.slots.add(slot1);
    }

    protected PartyCenter(Parcel in) {
        id = in.readString();
        name = in.readString();
        contactNumber = in.readString();
        address = in.readString();
        ownerId = in.readString();
        position = in.readParcelable(LatLng.class.getClassLoader());
        capacity = in.readInt();
    }

    public static final Creator<PartyCenter> CREATOR = new Creator<PartyCenter>() {
        @Override
        public PartyCenter createFromParcel(Parcel in) {
            return new PartyCenter(in);
        }

        @Override
        public PartyCenter[] newArray(int size) {
            return new PartyCenter[size];
        }
    };

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public LatLng getPosition() {
        return position;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public String getOwnerId() {
        return ownerId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }

    public Map<Date, Integer> getReservationOnADate() {
        return reservationOnADate;
    }

    public void setReservationOnADate(Map<Date, Integer> reservationOnADate) {
        this.reservationOnADate = reservationOnADate;
    }
    public void incrementDate(Date date,int n)
    {
        Integer number = reservationOnADate.get(date);
        reservationOnADate.put(date,number+n);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removePrev()
    {
        for(Map.Entry<Date,Integer> entry : reservationOnADate.entrySet())
        {
            Date date = entry.getKey();
            Date currentDate = new Date(System.currentTimeMillis());
            if(date.compareTo(currentDate) < 0)
            {
                reservationOnADate.remove(entry.getKey(),entry.getValue());
            }
        }
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(contactNumber);
        parcel.writeString(address);
        parcel.writeString(ownerId);
        parcel.writeParcelable(position, i);
        parcel.writeInt(capacity);
    }


}
