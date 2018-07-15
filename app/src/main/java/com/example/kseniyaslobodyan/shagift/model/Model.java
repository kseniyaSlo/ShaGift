package com.example.kseniyaslobodyan.shagift.model;

import android.net.Uri;
import android.support.annotation.NonNull;

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

public class Model {

    static Model instance = new Model();

    private Model() {
    }


    public void addNewPost(String namePost, String postDescription, String img) {
        Post m = new Post(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), namePost,postDescription, img);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        mRef.child(mRef.push().getKey()).setValue(m);
    }

    public FirebaseRecyclerOptions<Post> getAllPosts() {

      //will work with no Wifi
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        FirebaseRecyclerOptions<Post> options = new FirebaseRecyclerOptions.Builder<Post>().
                setQuery(mRef, Post.class).build();
        return options;
    }


    /*
     String author;
    String namePost;
    String postDescription;
    String image;

     */

    public void upload(Uri file, String author, final String namePost, final String postDescription){
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images");
        StorageReference ref = storageRef.child("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child(author);

        ref.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       // @SuppressWarnings("VisibleForTests")
                      //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        Model.instance.addNewPost(namePost,postDescription, downloadUrl.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                });
    }


}
