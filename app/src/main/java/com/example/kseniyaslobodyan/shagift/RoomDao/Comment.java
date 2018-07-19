package com.example.kseniyaslobodyan.shagift.RoomDao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "comments")
public class Comment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentname() {
        return commentname;
    }

    public void setCommentname(String commentname) {
        this.commentname = commentname;
    }

    public String getCommentdesc() {
        return commentdesc;
    }

    public void setCommentdesc(String commentdesc) {
        this.commentdesc = commentdesc;
    }

    public String getGiftpostId() {
        return giftpostId;
    }

    public void setGiftpostId(String giftpostId) {
        this.giftpostId = giftpostId;
    }

    @ColumnInfo(name = "comment_name")
    private String commentname;

    @ColumnInfo(name = "comment_desc")
    private String commentdesc;

    @ColumnInfo(name = "giftpost_id")
    private String giftpostId;


    public Comment(){}

    public Comment(String commentname, String commentdesc, String giftpostId) {
        this.commentname = commentname;
        this.commentdesc = commentdesc;
        this.giftpostId=giftpostId;
    }

    @Override
    public String toString() {
        return commentname + ": \n" + commentdesc ;
    }
}
