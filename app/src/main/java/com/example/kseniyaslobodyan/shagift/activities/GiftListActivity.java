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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.Post;
import com.example.kseniyaslobodyan.shagift.model.PostEvent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class GiftListActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "MyTag";
    private static final int REQUEST_IMAGE_CAPTURE = 333;
    private static final int REQUEST_WRITE_STORAGE = 444;
    private static final int GET_FROM_GALLERY = 555;
    PostAdapter adapter;;
    RecyclerView lstViewFeed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gift_list );

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled( true );
        actionBar.setCustomView( R.layout.shagift_action_bar_layoutforlist );

        ImageButton btnLeft = (ImageButton) findViewById( R.id.actionBar_btndashboard );
        btnLeft.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( GiftListActivity.this, MainActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP );
                startActivity( intent );
            }
        } );

        ImageButton btnmenuHome = (ImageButton) findViewById(R.id.actionBar_btndashboard);
        btnmenuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiftListActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        adapter = new PostAdapter( ModelPost.instance.getAllPosts() );
        lstViewFeed = (RecyclerView) findViewById( R.id.lstViewFeed );
        lstViewFeed.setLayoutManager( new LinearLayoutManager( this ) );
        lstViewFeed.setAdapter( adapter );
    }


    class PostHolder extends RecyclerView.ViewHolder{

        public TextView authorPost;
        public TextView postName;
        public TextView postDesc;
        public ImageView imgGift;
       // public LinearLayout linearLayout;

        public PostHolder(View itemView) {
            super(itemView);
            authorPost = (TextView) itemView.findViewById(R.id.feed_authorpost);
            postName = (TextView) itemView.findViewById(R.id.feed_postnmame);
            postDesc = (TextView) itemView.findViewById(R.id.feed_postdesc);
            imgGift = (ImageView) itemView.findViewById(R.id.feed_post_img);
            //linearLayout = (LinearLayout) itemView.findViewById( R.id.linearLayout );
        }
    }

    class PostAdapter extends FirebaseRecyclerAdapter<Post,PostHolder> {

        private FirebaseAuth auth;

        public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options) {
            super(options);
        }

        protected void onBindViewHolder(PostHolder holder, int position, @NonNull Post post) {
            holder.authorPost.setText(post.getAuthorName());
            holder.postName.setText(post.getNamePost());
            holder.postDesc.setText(post.getPostDescription());
            //holder.linearLayout.s

            if (!post.getImage().isEmpty()) {
                Glide.with(GiftListActivity.this)
                        .load(post.getImage())
                        .into(holder.imgGift);
            }
        }

       // LinearLayout bottom_menu = (LinearLayout)findViewById(R.id.bottom_menu);

       /* ImageButton btnfaivorite = (ImageButton) feed_post_layout.findViewById(R.id.bottom_menu_faivorite);
        btnfaivorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FaivoritesActivity.class);
                startActivity(intent);
                finish();

            }
        });*/

        public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_post_layout, parent, false);
            return new PostHolder(v);

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PostEvent event) {
        Toast.makeText(this, event.post, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK)
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        EventBus.getDefault().register(this);


    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        EventBus.getDefault().unregister(this);
    }

}

