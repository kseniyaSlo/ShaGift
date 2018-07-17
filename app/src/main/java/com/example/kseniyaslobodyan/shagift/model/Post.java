package com.example.kseniyaslobodyan.shagift.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "posts")

public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "authorId")
    private String authorId;

    @ColumnInfo(name = "authorName")
    private String authorName;

    @ColumnInfo(name = "namePost")
    private String namePost;

    @ColumnInfo(name = "postDescription")
    private String postDescription;

    @ColumnInfo(name = "image")
    private String image;

    public Post() {
    }

    public Post(int id, String authorId, String authorName,  String namePost,String postDescription, String image) {
        this.id=id;
        this.authorId = authorId;
        this.authorName=authorName;
        this.namePost = namePost;
        this.postDescription = postDescription;
        this.image = image;
    }


    public int getId() {
        return id;
    }
    public String getAuthorId() {
        return authorId;
    }
    public String getAuthorName() {
        return authorName;
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


    public void setId(int id) {
        this.id = id;
    }
    public void setAuthorId(String authorId) {
        this.authorId= authorId;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    @Override
    public String toString() {
        return authorId + "," + authorName + "," + namePost + "," + postDescription+ " ," + image ;
    }
}
