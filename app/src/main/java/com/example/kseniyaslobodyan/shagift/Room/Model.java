package com.example.kseniyaslobodyan.shagift.Room;

import com.example.kseniyaslobodyan.shagift.Room.MyApplication;
import com.example.kseniyaslobodyan.shagift.model.Post;

import java.util.List;

public class Model {

    private static Model instance = new Model();

    public static Model getInstance(){
        return instance;
    }

    public List<Post> getAllStudents(){
        return MyDatabase.getInstance(MyApplication.context).studentDao().getAll();
    }

    public void addStudent(Post student){
        MyDatabase.getInstance(MyApplication.context).studentDao().insertAll(student);
    }

    public void updateStudent(Post student){
        MyDatabase.getInstance(MyApplication.context).studentDao().update(student);
    }

    public void deleteStudent(Post student){
        MyDatabase.getInstance(MyApplication.context).studentDao().delete(student);
    }

   /* public Post getStudentByName(String firstName, String lastName){
        return MyDatabase.getInstance(MyApplication.context).studentDao().findByName(firstName, lastName);
    }*/

    public int countStudents(){
        return MyDatabase.getInstance(MyApplication.context).studentDao().countUsers();
    }
}
