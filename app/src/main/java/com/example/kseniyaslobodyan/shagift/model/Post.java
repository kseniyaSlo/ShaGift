package com.example.kseniyaslobodyan.shagift.model;

public class Post {
    String author;
    String namePost;
    String postDescription;
    String image;

    public Post() {
    }

    public Post(String author, String namePost,String postDescription, String image) {
        this.author = author;
        this.namePost = namePost;
        this.postDescription = postDescription;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public String getNamePost() {
        return namePost;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public String getImage() {
        return image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
