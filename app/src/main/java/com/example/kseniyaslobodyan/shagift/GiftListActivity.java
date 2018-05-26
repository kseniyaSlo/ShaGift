package com.example.kseniyaslobodyan.shagift;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GiftListActivity extends AppCompatActivity {

    List<GiftPost> giftPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list);

        ListView lstViewFeed = (ListView) findViewById(R.id.lstFeed);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.shagift_action_bar_layoutforlist);

        ImageButton btnLeft = (ImageButton) findViewById(R.id.actionBar_btndashboard);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiftListActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        //The post generations
        giftPosts = generatePosts();
        final ListAdapter listAdapter = new ListAdapter(giftPosts);
        lstViewFeed.setAdapter(listAdapter);
        lstViewFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiftPost gp = giftPosts.get(position);
               // gp.select();
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    private List<GiftPost> generatePosts() {

        //Hard Code data until the server will connected
        int[] images = {R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.image7,
                R.drawable.image8,
                R.drawable.image9};

        User user1 = new User("Kseniya1","tes1",R.drawable.post_profile1);
        User user2 = new User("Kseniya2","tes2",R.drawable.post_profile1);
        User user3 = new User("Kseniya3","tes3",R.drawable.post_profile1);

        List<GiftPost> gPosts = new ArrayList<>();
        {
            GiftPost gp = new GiftPost(1,R.string.abc_an_amazing_alphabet_book,user1,R.string.now,R.drawable.image1);
            gPosts.add(gp);

            gp = new GiftPost(2,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image2);
            gPosts.add(gp);

            gp = new GiftPost(3,R.string.abc_an_amazing_alphabet_book,user3,R.string.now,R.drawable.image3);
            gPosts.add(gp);

            gp = new GiftPost(4,R.string.abc_an_amazing_alphabet_book,user1,R.string.now,R.drawable.image5);
            gPosts.add(gp);

            gp = new GiftPost(7,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image7);
            gPosts.add(gp);

            gp = new GiftPost(5,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image6);
            gPosts.add(gp);

            gp = new GiftPost(6,R.string.abc_an_amazing_alphabet_book,user3,R.string.now,R.drawable.image4);
            gPosts.add(gp);

            gp = new GiftPost(9,R.string.abc_an_amazing_alphabet_book,user3,R.string.now,R.drawable.image8);
            gPosts.add(gp);

        }
        return gPosts;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
