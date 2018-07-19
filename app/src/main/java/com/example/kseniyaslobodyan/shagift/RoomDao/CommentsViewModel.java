package com.example.kseniyaslobodyan.shagift.RoomDao;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;


public class CommentsViewModel extends AndroidViewModel {

    private Repository mRepository;

    private LiveData<List<Comment>> comments;

    public CommentsViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);
        comments = mRepository.getAllStudents();
    }

    public LiveData<List<Comment>> getAllComments() { return comments; }

    public void add(Comment comment) { mRepository.add(comment); }


}
