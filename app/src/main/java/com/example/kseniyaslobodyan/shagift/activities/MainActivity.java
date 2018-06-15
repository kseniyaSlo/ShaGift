package com.example.kseniyaslobodyan.shagift.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.kseniyaslobodyan.shagift.adapters.DashboardAdapter;
import com.example.kseniyaslobodyan.shagift.model.GiftPost;
import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.User;


public class MainActivity extends AppCompatActivity {

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

    //int id, int nameGift,  User author, int whenPosted, int imageUrl,
    private GiftPost[] giftPos = {
            new GiftPost(1,R.string.abc_an_amazing_alphabet_book,user1,R.string.now,R.drawable.image1),
            new GiftPost(2,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image2),
            new GiftPost(3,R.string.abc_an_amazing_alphabet_book,user3,R.string.now,R.drawable.image3),
            new GiftPost(4,R.string.abc_an_amazing_alphabet_book,user1,R.string.now,R.drawable.image5),
            new GiftPost(5,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image6),
            new GiftPost(6,R.string.abc_an_amazing_alphabet_book,user3,R.string.now,R.drawable.image4),
            new GiftPost(7,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image7),
            new GiftPost(9,R.string.abc_an_amazing_alphabet_book,user3,R.string.now,R.drawable.image8),
            new GiftPost(2,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image2),
            new GiftPost(3,R.string.abc_an_amazing_alphabet_book,user3,R.string.now,R.drawable.image3),
            new GiftPost(4,R.string.abc_an_amazing_alphabet_book,user1,R.string.now,R.drawable.image5),
            new GiftPost(5,R.string.abc_an_amazing_alphabet_book,user2,R.string.now,R.drawable.image6)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* RecyclerView lstGrid = (RecyclerView) findViewById(R.id.lstGrid);
        GridAdapter gridAdapter = new GridAdapter(this, images);
        lstGrid.setAdapter(gridAdapter);
        lstGrid.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

*/
        GridView gridView = (GridView)findViewById(R.id.gridDashboard);
        final DashboardAdapter dashboardAdapter = new DashboardAdapter(this, giftPos);
        gridView.setAdapter(dashboardAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiftPost giftpost = giftPos[position];
                dashboardAdapter.notifyDataSetChanged();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.shagift_action_bar_layout);

        ImageButton btnLeft = (ImageButton) findViewById(R.id.actionBar_btnList);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GiftListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                }
        });

        ImageButton btnRight = (ImageButton) findViewById(R.id.actionBar_btnsettings);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuBarActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    //    LinearLayout bottom_menu = (LinearLayout)findViewById(R.id.bottom_menu);

    }


}

