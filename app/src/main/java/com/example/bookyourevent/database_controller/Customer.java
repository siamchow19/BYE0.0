package com.example.bookyourevent.database_controller;

import java.io.Serializable;

public class Customer implements Serializable {
    private String id,name,address,phoneNumber,imageURI,email;
    public Customer()
    {

    }
    public Customer(String name,String address,String phoneNumber,String imageURI)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURI = imageURI;
    }
    public Customer(String name,String address,String phoneNumber,String imageURI,String id)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURI = imageURI;
        this.id = id;
    }
    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getImageURI() {
        return imageURI;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
