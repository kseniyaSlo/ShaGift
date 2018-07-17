package com.example.kseniyaslobodyan.shagift.ViewModel;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.kseniyaslobodyan.shagift.model.Post;

import java.util.List;

@Dao
public interface StudentDao2 {

    @Insert
    void insertAll(List<Post> users);

    /*
       private String authorName;
    private String namePost;
    private String postDescription;
    private String image;

    * */

    //@Query("SELECT id,authorId,authorName,namePost,postDescription,image FROM posts")
    @Query("SELECT * FROM posts")
    List<Post> getAll();

    @Query("DELETE FROM posts")
    void clear();
}
