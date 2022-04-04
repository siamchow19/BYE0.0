package com.example.bookyourevent.database_controller;

public class Owner {
    private String id,name,email,phoneNumber,tinNumber,address;
    public Owner()
    {

    }
    public Owner(String name,String email,String phoneNumber,String tinNumber,String address)
    {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tinNumber = tinNumber;
        this.address = address;
    }
    public Owner(String id,String name,String email,String phoneNumber,String tinNumber,String address)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.tinNumber = tinNumber;
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

}
