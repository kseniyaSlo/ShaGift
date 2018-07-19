package com.example.kseniyaslobodyan.shagift.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.kseniyaslobodyan.shagift.model.Post;
import com.example.kseniyaslobodyan.shagift.model.PostEvent;
import com.example.kseniyaslobodyan.shagift.RoomDao.CommentsActivity;
import com.example.kseniyaslobodyan.shagift.testGridViewFirebase.TestActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kseniyaslobodyan.shagift.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity  {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "MyTag";
    private static final int REQUEST_IMAGE_CAPTURE = 333;
    private static final int REQUEST_WRITE_STORAGE = 444;
    private static final int GET_FROM_GALLERY = 555;
    PostAdapter adapter;;
    RecyclerView lstViewFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled( true );
        actionBar.setCustomView( R.layout.shagift_action_bar_layoutforlist );

        ImageButton btnLeft = (ImageButton) findViewById( R.id.actionBar_btndashboard );
        btnLeft.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, GiftListActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP );
                startActivity( intent );
            }
        } );

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
        public Button btnaddtofavorites;
        public Button btnaddcomment;

        // public LinearLayout linearLayout;

        public PostHolder(View itemView) {
            super(itemView);
            authorPost = (TextView) itemView.findViewById(R.id.feed_authorpost);
            postName = (TextView) itemView.findViewById(R.id.feed_postnmame);
            postDesc = (TextView) itemView.findViewById(R.id.feed_postdesc);
            imgGift = (ImageView) itemView.findViewById(R.id.feed_post_img);
            btnaddtofavorites = (Button) itemView.findViewById(R.id.btnaddtofavorites);
            btnaddcomment = (Button) itemView.findViewById(R.id.btnaddcomment);
        }
    }

    class PostAdapter extends FirebaseRecyclerAdapter<Post,PostHolder> {

        private FirebaseAuth auth;

        public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options) {
            super(options);
        }

        protected void onBindViewHolder(PostHolder holder, int position, @NonNull final Post post) {
            holder.authorPost.setText(post.getAuthorName());
            holder.postName.setText(post.getNamePost());
            holder.postDesc.setText(post.getPostDescription());

            //button of add to faivorite
            holder.btnaddtofavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();

                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("FavoritesPosts/" + userId );
                    mRef.child(mRef.push().getKey()).setValue(post);
                    Toast.makeText(MainActivity.this, "The gift's post has been successfully added to the favorites tab" , Toast.LENGTH_SHORT).show();
                }
            });

            //button of add to comments
            holder.btnaddcomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                    startActivity(intent);
                    finish();

                }
            });

            if (!post.getImage().isEmpty()) {
                Glide.with(MainActivity.this)
                        .load(post.getImage())
                        .into(holder.imgGift);
            }
        }

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


        /***ADD To ALL**/
        LinearLayout bottom_menu = (LinearLayout)findViewById(R.id.bottom_menu);

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
                Intent intent = new Intent(MainActivity.this, FaivoritesActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //userprofile button in the bottom menu
        ImageButton btnuserprofile = (ImageButton) bottom_menu.findViewById(R.id.bottom_menu_userprofile);
        btnuserprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();


            }
        });

        //My post button in the bottom menu
        ImageButton btnmyposts = (ImageButton) bottom_menu.findViewById(R.id.bottom_menu_myposts);
        btnmyposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPostActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }

    //ust as we did when creating our SearchView widget, we'll inflate our new menu in an onCreateOptionsMenu() method within MainActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //tell our app what action to perform when the user selects the logout option.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        else if (id == R.id.action_about) {
            About();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //logout and move to login screen
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        EventBus.getDefault().unregister(this);
    }

    private void About() {
    }
}

