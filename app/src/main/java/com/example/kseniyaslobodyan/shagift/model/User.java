package com.example.kseniyaslobodyan.shagift.model;

public class User {
    String userId;
    String name;
    String email;
    int profileImage;

    public User(String userId, String name, String email, int profileImage) {
        this.userId=userId;
        this.name = name;
        this.email= email;
        this.profileImage = profileImage;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }


    public int getProfileImage() {
        return profileImage;
    }
}
