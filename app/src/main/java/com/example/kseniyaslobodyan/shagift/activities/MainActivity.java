package com.example.kseniyaslobodyan.shagift.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.kseniyaslobodyan.shagift.firebase.TestActivity;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.LinearLayout;


import com.example.kseniyaslobodyan.shagift.R;
import com.google.firebase.database.DatabaseReference;


public class MainActivity extends AppCompatActivity  {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    DatabaseReference mRef;
    GridView gridView;
    EditText nameEditTxt,propTxt,descTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridDashboard);


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
    }


    @Override
    protected void onStart() {
        super.onStart();


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

    private void About() {
    }
}

