package com.example.kseniyaslobodyan.shagift.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
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
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.Post;
import com.example.kseniyaslobodyan.shagift.model.PostEvent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MyPostActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "MyTag";
    private static final int REQUEST_IMAGE_CAPTURE = 333;
    private static final int REQUEST_WRITE_STORAGE = 444;
    private static final int GET_FROM_GALLERY = 555;
    PostAdapter adapter;
    RecyclerView lstMessages;
    ImageView imgCapture;
    Bitmap imageBitmap;
    static String userName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        adapter = new PostAdapter(ModelPost.instance.getAllMyPosts());

        lstMessages = (RecyclerView) findViewById(R.id.lstMessages);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));
        lstMessages.setAdapter(adapter);

    }

    class PostHolder extends RecyclerView.ViewHolder{

        public TextView tvAuthor;
        public TextView tvName;
        public TextView tvDesc;
        public ImageView imgPost;
        public Button btnremovepost;
        public Button btneditpost;


        public PostHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
            imgPost = (ImageView) itemView.findViewById(R.id.imgPost);
            btnremovepost = (Button) itemView.findViewById(R.id.btnremovepost);
        }
    }


    class PostAdapter extends FirebaseRecyclerAdapter<Post,PostHolder> {

        private FirebaseAuth auth;

        public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options) {
            super(options);
        }

        protected void onBindViewHolder(PostHolder holder, int position, @NonNull Post post) {
            holder.tvAuthor.setText(post.getAuthorName());
            holder.tvName.setText(post.getNamePost());
            holder.tvDesc.setText(post.getPostDescription());

            holder.btnremovepost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();

                    Toast.makeText(MyPostActivity.this, "The post is removed from the ShaGift" , Toast.LENGTH_SHORT).show();
                }
            });

            if (!post.getImage().isEmpty()) {
                Glide.with(MyPostActivity.this)
                        .load(post.getImage())
                        .into(holder.imgPost);
            }
        }

        public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myposts_layout, parent, false);
            return new PostHolder(v);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PostEvent event) {
        Toast.makeText(this, event.post, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        EventBus.getDefault().register(this);

        /***ADD To ALL**/
        LinearLayout bottom_menu = (LinearLayout)findViewById(R.id.bottom_menu);


        /*** TEST ACTIVITY!!!!***/
        //faivorite button in the bottom menu
        ImageButton btnhome = (ImageButton) bottom_menu.findViewById(R.id.bottom_menu_home);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPostActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //add post gift button in the bottom menu
        ImageButton btnaddpostgift = (ImageButton) bottom_menu.findViewById(R.id.bottom_menu_addgift);
        btnaddpostgift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start the new post fragment
                CustomDialog customDialog = new CustomDialog();
                customDialog.show(getSupportFragmentManager(), "");
            }
        });

        /*** TEST ACTIVITY!!!!***/
        //faivorite button in the bottom menu
        ImageButton btnfaivorite = (ImageButton) bottom_menu.findViewById(R.id.bottom_menu_faivorite);
        btnfaivorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPostActivity.this, FaivoritesActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //userprofile button in the bottom menu
        ImageButton btnuserprofile = (ImageButton) bottom_menu.findViewById(R.id.bottom_menu_userprofile);
        btnuserprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPostActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK)
            adapter.notifyDataSetChanged();

    }

}

