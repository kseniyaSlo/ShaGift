package com.example.kseniyaslobodyan.shagift.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.kseniyaslobodyan.shagift.model.Post;

@Database(entities = {Post.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract StudentDao studentDao();

    public static MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                                            MyDatabase.class,
                                            "mydatabase").build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
