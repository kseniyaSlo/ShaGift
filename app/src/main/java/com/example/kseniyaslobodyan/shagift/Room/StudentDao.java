package com.example.kseniyaslobodyan.shagift.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kseniyaslobodyan.shagift.model.Post;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insertAll(Post... posts);

    @Delete
    void delete(Post user);

    @Update
    void update(Post user);

   /* @Query("SELECT * FROM students where first_name LIKE  :firstName AND last_name LIKE :lastName")
    Student findByName(String firstName, String lastName);*/

    @Query("SELECT COUNT(*) from posts")
    int countUsers();

    @Query("SELECT * FROM posts")
    List<Post> getAll();

  /*  @Query("DELETE FROM posts")
    void clear();*/
}

