package com.example.kseniyaslobodyan.shagift.ViewModel;

import android.util.Log;

import com.example.kseniyaslobodyan.shagift.model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class ModelFirebase {

    private static final String TAG = "MyTag";
    public static ModelFirebase instance = new ModelFirebase();

    private ModelFirebase(){}

    public void addStudent(Post student){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("Posts");
        ref.child(ref.push().getKey()).setValue(student);
    }

    public void cancellGetAllStudents() {
        DatabaseReference stRef = FirebaseDatabase.getInstance().getReference().child("Students");
        stRef.removeEventListener(eventListener);
    }

    interface GetAllStudentsListener{
        public void onSuccess(List<Post> studentslist);
    }

    ValueEventListener eventListener;

    public void getAllStudents(final GetAllStudentsListener listener) {
        DatabaseReference stRef = FirebaseDatabase.getInstance().getReference().child("Students");

        Log.d(TAG, "Getting from firebase");

        eventListener = stRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Post> stList = new LinkedList<>();

                Log.d(TAG, "Back from firebase");

                for (DataSnapshot stSnapshot: dataSnapshot.getChildren()) {
                    Post st = stSnapshot.getValue(Post.class);
                    stList.add(st);
                }

                Log.d(TAG, stList.size() + " from firebase");

                listener.onSuccess(stList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
