package com.example.kseniyaslobodyan.shagift.RoomDao;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Comment.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance;

    public abstract CommentDao commentDao();

    public static MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class,
                    "commentsdatabase").build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}