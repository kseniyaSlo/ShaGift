package com.example.kseniyaslobodyan.shagift.firebase;

import android.util.Log;

import com.example.kseniyaslobodyan.shagift.model.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


/**
 * 1.SAVE DATA TO FIREBASE
 * 2. RETRIEVE
 * 3.RETURN AN ARRAYLIST
 */
public class FirebaseHelper {

    private static final String TAG = "TAGA";
    DatabaseReference db;
    private FirebaseAuth auth;

    Boolean saved=null;
    ArrayList<Post> giftposts=new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }
    //WRITE IF NOT NULL
    public Boolean save(Post post)
    {
        if(post==null) {
            saved=false;
        }else
        {
            try {
                db.child("testposts").push().setValue(post);
                Log.d(TAG, "aaaaaaaaaa   "  + post.getAuthorId());

                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }
    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot) {
        giftposts.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Post post = ds.getValue( Post.class );
            String temp=post.getAuthorName();
            if (temp == null) {
              //  Log.d( TAG, "NULLLLLL   " + post.getAuthorName() );
               // Log.d( TAG, "POSSSSTTT   " + post.getAuthorName() );

            } else {

               // Log.d( TAG, "AAAAAAAAAAAAAAAAAA   " + post.getAuthorName() );

                giftposts.add( post );

               // Log.d( TAG, "POSSSSTTT   " + post.getAuthorName() );
               // Log.d( TAG, "POSSSSTTT   " + post.getId() );
              //  Log.d( TAG, "POSSSSTTT   " + post.getNamePost() );
              //  Log.d( TAG, "POSSSSTTT   " + post.getPostDescription() );
            }
        }

        Log.d( TAG, "ZZZZZZZZZZZZZZZZZ   "  );
        System.out.println(giftposts);

    }

    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS
    public ArrayList<Post> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
              //  fetchData(dataSnapshot);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //fetchData(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return giftposts;
    }
}
