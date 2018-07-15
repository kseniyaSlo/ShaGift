package com.example.kseniyaslobodyan.shagift.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.kseniyaslobodyan.shagift.R;


public class AboutActivity extends AppCompatActivity {

    private Button signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

       // signOut = (Button) findViewById(R.id.sign_out);




      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.shagift_action_bar_layout);*/
    }

    //sign out method
   /* public void signOut() {
        auth.signOut();
    }*/
}
