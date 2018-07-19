package com.example.kseniyaslobodyan.shagift.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.util.Log;

import static android.app.Activity.RESULT_OK;

import com.example.kseniyaslobodyan.shagift.R;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;


public class CustomDialog extends DialogFragment {

    private static final int REQUEST_IMAGE_CAPTURE = 111;
    private static final int REQUEST_WRITE_STORAGE = 222;
    private static final int PICK_IMAGE = 333;

    ImageView imgNew;
    Bitmap imageBitmap;
    Uri fileToUpload;
    private VideoView videoPlayer;


    public interface OnClickListener{
        void onClick(int id);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.activity_new_post, null));

        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            imgNew.setImageBitmap(imageBitmap);

            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            final File imageFile = new File(dir,"capture.jpg");
            fileToUpload = Uri.fromFile(imageFile);

            boolean hasPermission = (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
            if (!hasPermission) {
                ActivityCompat.requestPermissions((Activity) getContext(),new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
            }
            else{
                saveToFile("capture.jpg", imageBitmap);


            }
        }
        else if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            fileToUpload = data.getData();

            if(getFileName(fileToUpload).endsWith("mp4")){
                videoPlayer.setVideoURI(fileToUpload);
                videoPlayer.setMediaController(new MediaController(getActivity()));
                videoPlayer.requestFocus();
                videoPlayer.setVisibility(View.VISIBLE);
                imgNew.setVisibility(View.GONE);
                videoPlayer.start();
            }
            else {
                Glide.with(getContext())
                        .load(fileToUpload)
                        .into(imgNew);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveToFile("capture.jpg", imageBitmap);
                }
                return;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        getDialog().setTitle("New post");

        Dialog d = getDialog();

        View btnCapture = d.findViewById(R.id.btnCapture);
        imgNew = (ImageView) d.findViewById(R.id.imgNew);
        Button btnPost = (Button) d.findViewById(R.id.btnPost);
        final EditText etName = (EditText) d.findViewById(R.id.etName);
        final EditText etDesc = (EditText) d.findViewById(R.id.etDesc);

        Button btnGallery = d.findViewById(R.id.btnGallery);
        videoPlayer = d.findViewById(R.id.videoPlayer);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

       /* btnAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap imageBitmap = loadImageFromFile("capture.jpg");
                imgNew.setImageBitmap(imageBitmap);

                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                final File imageFile = new File(dir,"capture.jpg");
                fileToUpload = Uri.fromFile(imageFile);
            }
        });*/

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stretMsg = etName.getText().toString();
                String stretDesc = etDesc.getText().toString();
                String strAuthorId = getFileName(fileToUpload);
                //  from model.java -> public void upload(Uri file, String author, final String namePost, final String postDescription){
                ModelPost.instance.upload(fileToUpload,strAuthorId, stretMsg ,stretDesc);
                dismiss();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // EventBus.getDefault().post(new MessageEvent("Hello everyone!"));

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/* video/*");
                startActivityForResult(Intent.createChooser(intent, "Select file"), PICK_IMAGE);
            }
        });
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    //Read Local Image File
    private Bitmap loadImageFromFile(String imageFileName){
        Bitmap bitmap = null;
        try {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File imageFile = new File(dir,imageFileName);
            InputStream inputStream = new FileInputStream(imageFile);
            bitmap = BitmapFactory.decodeStream(inputStream);
            Log.d("tag","got image from cache: " + imageFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //Save Local Image File
    private void saveToFile(String name, Bitmap imageBitmap) {
        try {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File imageFile = new File(dir,name);
            imageFile.createNewFile();
            OutputStream out = new FileOutputStream(imageFile);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


