package com.example.kseniyaslobodyan.shagift.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kseniyaslobodyan.shagift.model.Post;

import java.util.LinkedList;
import java.util.List;

import retrofit2.http.POST;

public class Repository {
    private static final String TAG = "MyTag";
    private StudentDao2 dao;
    private StudentListData studentListData;

    public Repository(Application application) {
        MyDatabase2 db = MyDatabase2.getInstance(application);
        dao = db.studentDao();
        studentListData = new StudentListData();
    }

    public void cancellGetAllStudents() {
        ModelFirebase.instance.cancellGetAllStudents();
    }

    class StudentListData extends MutableLiveData<List<Post>> {

        @Override
        protected void onActive() {
            super.onActive();

            Log.d("MyTag", "OnActive");

            new StudentsAsyncTask().execute(new Runnable() {
                @Override
                public void run() {

                    List<Post> s = dao.getAll();
                    Log.d(TAG, s.size() + " students in the database");
                    postValue(s);

                    ModelFirebase.instance.getAllStudents(new ModelFirebase.GetAllStudentsListener() {
                        @Override
                        public void onSuccess(final List<Post> studentslist) {

                            Log.d(TAG, studentslist.size() + " students from firebase");

                            setValue(studentslist);

                            new StudentsAsyncTask().execute(new Runnable() {
                                @Override
                                public void run() {
                                    dao.clear();
                                    dao.insertAll(studentslist);
                                }
                            });
                        }
                    });
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            Log.d("MyTag", "OnInActive");

            cancellGetAllStudents();
        }

        public StudentListData() {
            super();
            setValue(new LinkedList<Post>());
        }
    }

    public LiveData<List<Post>> getAllStudents() {
        return studentListData;
    }

    public void add (final Post student) {
        new StudentsAsyncTask().execute(new Runnable() {
            @Override
            public void run() {
                ModelFirebase.instance.addStudent(student);
            }
        });
    }

    private static class StudentsAsyncTask extends AsyncTask<Runnable, Void, Void> {

        @Override
        protected Void doInBackground(final Runnable... params) {
            params[0].run();
            return null;
        }
    }
}

