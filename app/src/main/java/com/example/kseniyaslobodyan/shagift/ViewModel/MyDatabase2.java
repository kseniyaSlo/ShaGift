package com.example.kseniyaslobodyan.shagift.ViewModel;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.kseniyaslobodyan.shagift.model.Post;

@Database(entities = {Post.class}, version = 1)
public abstract class MyDatabase2 extends RoomDatabase {

    private static MyDatabase2 instance;

    public abstract StudentDao2 studentDao();

    public static MyDatabase2 getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                                            MyDatabase2.class,
                                            "mydatabase").build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
