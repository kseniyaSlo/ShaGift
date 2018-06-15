package com.example.kseniyaslobodyan.shagift.model;

public class User {
    String firstName;
    String lastName;
    int profileImage;

    public User(String firstName, String lastName,int profileImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
    }

    public String getfirstName() {
        return this.firstName;
    }

    public String getlastName() {
        return this.lastName;
    }

    public int getProfileImage() {
        return profileImage;
    }
}
