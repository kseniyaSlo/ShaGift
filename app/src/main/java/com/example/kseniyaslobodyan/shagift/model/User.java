package com.example.kseniyaslobodyan.shagift.model;

public class User {
    String Name;
    int profileImage;

    public User(String Name,int profileImage) {
        this.Name = Name;
        this.profileImage = profileImage;
    }

    public String getName() {
        return this.Name;
    }

    public int getProfileImage() {
        return profileImage;
    }
}
