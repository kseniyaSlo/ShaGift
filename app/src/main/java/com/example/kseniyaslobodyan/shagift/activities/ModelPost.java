package com.example.kseniyaslobodyan.shagift.activities;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.kseniyaslobodyan.shagift.model.Post;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;


public class ModelPost {

    static ModelPost instance = new ModelPost();
    private static final String TAG = "MyTag";
    private FirebaseAuth auth;

    private ModelPost() {
    }


    //Upload new post
    public void upload(Uri file, String authorId, final String namePost, final String postDescription) {

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {

            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images");
            StorageReference ref = storageRef.child("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child(authorId);

            ref.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            ModelPost.instance.addNewPost(namePost, postDescription, downloadUrl.toString());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                        }
                    });
        }
    }


    public void addNewPost(String namePost, String postDescription, String img) {


        if (auth.getCurrentUser() != null) {

            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String AuthorName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            Post giftPost = new Post(1,userID, AuthorName, namePost, postDescription, img);

            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Posts");
            mRef.child(mRef.push().getKey()).setValue(giftPost);
        }
    }

    public FirebaseRecyclerOptions<Post> getAllPosts() {

        //will work with no Wifi
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        FirebaseRecyclerOptions<Post> options = new FirebaseRecyclerOptions.Builder<Post>().
                setQuery(mRef, Post.class).build();
        return options;
    }

}


