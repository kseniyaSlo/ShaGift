package com.example.kseniyaslobodyan.shagift.ViewModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.example.kseniyaslobodyan.shagift.model.Post;

import java.util.List;

public class StudentsViewModel2 extends AndroidViewModel {

    private Repository mRepository;

    private LiveData<List<Post>> students;

    public StudentsViewModel2(Application application) {
        super(application);
        mRepository = new Repository(application);
        students = mRepository.getAllStudents();
    }

    public LiveData<List<Post>> getAllStudents() { return students; }

    public void add(Post student) { mRepository.add(student); }


}
