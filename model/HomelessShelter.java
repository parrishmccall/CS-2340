package com.example.jpm.offthestreets.model;


public class HomelessShelter {

    //variables according to header list
    private int uniqueKey;
    private String shelterName;
    private int capacity;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String specialNotes;
    private String phoneNumber;


    public HomelessShelter() {
    }
    //Homeless Shelter Constructor
    public HomelessShelter(int uniqueKey, String shelterName, int capacity, String restrictions,
                double longitude, double latitude, String address, String specialNotes,
                String phoneNumber) {

        this.uniqueKey = uniqueKey;
        this.shelterName = shelterName;
        this.capacity = capacity;
        this.restrictions = restrictions;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.specialNotes = specialNotes;
        this.phoneNumber = phoneNumber;
    }

    public int getId() { return uniqueKey; }
    public String getShelterName() { return shelterName; }
    public int getCapacity() { return capacity; }
    public String getRestrictions() { return restrictions; }
    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }
    public String getAddress() { return address; }
    public String getSpecialNotes() { return specialNotes; }
    public String getPhoneNumber() { return phoneNumber; }

    public String getGender() {
        if (restrictions.toLowerCase().indexOf("men") != -1 && restrictions.toLowerCase().indexOf("women") == -1) {
            return "men";
        } else if (restrictions.toLowerCase().indexOf("women") != -1) {
            return "women";
        } else {
            return "both";
        }
    }

    public void setCapacity(int cap) {
        capacity = cap;
    }

    public String toString() {
        return uniqueKey + " " + shelterName + " " + capacity + " " + restrictions + " " + longitude + " " + latitude + " " + address
                + " " + specialNotes + " " + phoneNumber;
    }
}
