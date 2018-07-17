package com.example.kseniyaslobodyan.shagift.model;

public class GiftPost {
    User author;
    int namepost;
    int descpodt ;
    int imageUrl;
    //private boolean isFavorite = false;

    public GiftPost( User author, int namepost, int descpodt, int imageUrl) {
        this.author = author;
        this.namepost = namepost;
        this.imageUrl = imageUrl;
        this.descpodt = descpodt;
    }

    public int getNamePost() {
        return this.namepost;
    }

    public int getDescPodt() {
        return this.descpodt;
    }

    public User getAuthor() {
        return this.author;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void select() {
        // = this.nameGift + " selected";
    }
}
