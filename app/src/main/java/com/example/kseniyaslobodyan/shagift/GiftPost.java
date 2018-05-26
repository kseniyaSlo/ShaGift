package com.example.kseniyaslobodyan.shagift;

public class GiftPost {
    int id;
    int nameGift;
    int imageUrl;
    User author;
    int whenPosted;
    //private boolean isFavorite = false;

    public GiftPost(int id, int nameGift,  User author, int whenPosted, int imageUrl) {
        this.id = id;
        this.nameGift = nameGift;
        this.imageUrl = imageUrl;
        this.author = author;
        this.whenPosted = whenPosted;
    }

    public int getID() {
        return this.id;
    }

    public int getNameGift() {
        return this.nameGift;
    }

    public User getAuthor() {
        return this.author;
    }

    public int getWhenPosted() {
        return this.whenPosted;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public int getProfileImage () { return author.getProfileImage(); }

    public void select() {
        // = this.nameGift + " selected";
    }
}
