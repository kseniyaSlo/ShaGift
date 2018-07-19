package com.example.kseniyaslobodyan.shagift.RoomDao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert
    void insertAll(List<Comment> users);

    @Query("SELECT * FROM comments")
    List<Comment> getAll();

    @Query("DELETE FROM comments")
    void clear();


    @Query("SELECT * FROM comments where giftpost_id LIKE  :giftPostId")
    List<Comment> findCommentsByGiftPostId(String giftPostId);

    @Query("SELECT COUNT(*) from comments")
    int countUsers();
}
