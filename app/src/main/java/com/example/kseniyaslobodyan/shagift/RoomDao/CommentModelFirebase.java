package com.example.kseniyaslobodyan.shagift.RoomDao;

import android.util.Log;
import android.widget.Toast;

import com.example.kseniyaslobodyan.shagift.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.LinkedList;
import java.util.List;

public class CommentModelFirebase {

    private static final String TAG = "MyTag";
    ValueEventListener eventListener;
    public static CommentModelFirebase instance = new CommentModelFirebase();

    private CommentModelFirebase(){}

    public void addComment(Comment comment){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("Comments");
        ref.child(ref.push().getKey()).setValue(comment);

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        // DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("FavoritesPosts/" + userId );
        // mRef.child(mRef.push().getKey()).setValue(post);
        Log.d(TAG, "aaaaaaaaa - userId = " + userId);

    }

    public void cancellGetAllComments() {
        DatabaseReference stRef = FirebaseDatabase.getInstance().getReference().child("Comments");
        stRef.removeEventListener(eventListener);
    }

    interface GetAllCommentsListener{
        public void onSuccess(List<Comment> commentslist);
    }


    public void getAllComments(final GetAllCommentsListener listener) {

        DatabaseReference stRef = FirebaseDatabase.getInstance().getReference().child("Comments");

        Log.d(TAG, "Getting from firebase");

        eventListener = stRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Comment> stList = new LinkedList<>();

                Log.d(TAG, "Back from firebase");

                for (DataSnapshot stSnapshot: dataSnapshot.getChildren()) {
                    Comment st = stSnapshot.getValue(Comment.class);
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
