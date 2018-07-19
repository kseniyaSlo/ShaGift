package com.example.kseniyaslobodyan.shagift.activities;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.Post;
import com.example.kseniyaslobodyan.shagift.model.PostEvent;
import com.example.kseniyaslobodyan.shagift.testGridViewFirebase.TestActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class GiftListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gift_list );


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled( true );
        actionBar.setCustomView( R.layout.shagift_action_bar_layout);

        ImageButton btnLeft = (ImageButton) findViewById( R.id.actionBar_btndashboard );
        btnLeft.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( GiftListActivity.this, MainActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP );
                startActivity( intent );
            }
        } );
    }
}
