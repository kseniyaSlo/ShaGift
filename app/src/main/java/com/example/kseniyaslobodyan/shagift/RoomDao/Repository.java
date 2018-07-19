package com.example.kseniyaslobodyan.shagift.RoomDao;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;


import java.util.LinkedList;
import java.util.List;

public class Repository {
    private static final String TAG = "MyTag";
    private CommentDao dao;
    private CommentListData commentListData;

    public Repository(Application application) {
        MyDatabase db = MyDatabase.getInstance(application);
        dao = db.commentDao();
        commentListData = new CommentListData();
    }

    public void cancellGetAllStudents() {
        CommentModelFirebase.instance.cancellGetAllComments();
    }

    class CommentListData extends MutableLiveData<List<Comment>> {

        @Override
        protected void onActive() {
            super.onActive();

            Log.d("MyTag", "OnActive");

            new CommentsAsyncTask().execute(new Runnable() {
                @Override
                public void run() {

                    CommentModelFirebase.instance.getAllComments(new CommentModelFirebase.GetAllCommentsListener() {
                        @Override
                        public void onSuccess(final List<Comment> commentslist) {

                            Log.d(TAG, commentslist.size() + " Comments from firebase");

                            setValue(commentslist);

                            new CommentsAsyncTask().execute(new Runnable() {
                                @Override
                                public void run() {
                                    dao.clear();
                                    dao.insertAll(commentslist);
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

        public CommentListData() {
            super();
            setValue(new LinkedList<Comment>());
        }
    }

    public LiveData<List<Comment>> getAllStudents() {
        return commentListData;
    }

    public void add (final Comment comment) {
        new CommentsAsyncTask().execute(new Runnable() {
            @Override
            public void run() {
                CommentModelFirebase.instance.addComment(comment);
            }
        });
    }

    private static class CommentsAsyncTask extends AsyncTask<Runnable, Void, Void> {

        @Override
        protected Void doInBackground(final Runnable... params) {
            params[0].run();
            return null;
        }
    }
}

