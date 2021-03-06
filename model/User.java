package com.example.jpm.offthestreets.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User {

    public static List<String> userTypes = Arrays.asList("user", "admin");

    public static ArrayList<User> MyArr1 = new ArrayList<User>();

    private static int count = 0; //number of users in arraylist
    private String name;
    private String email;
    private String phone;
    private String password;
    private int bedsBooked = 0;
    private String shelterBookedAt = "" ;

    public User() {
    }

    //User details constructor
    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        count++;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return password;
    }

    public String getName() { return name; }

    public String getPhoneNumber() { return phone; }

    public int getBedsBooked() {
        return bedsBooked;
    }

    public String getShelterBookedAt() {
        return shelterBookedAt;
    }

    public void clearBooking() {
        this.clearBedsBooked();
        this.clearShelterBookAt();
    }

    public void clearBedsBooked() { bedsBooked = 0; }

    public void clearShelterBookAt() { shelterBookedAt = null; }

}
