package com.example.kseniyaslobodyan.shagift.testGridViewFirebase;

import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
        import android.widget.TextView;

import com.example.kseniyaslobodyan.shagift.R;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "TAGA";
    TextView nameTxt,descTxt, propTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_detail);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        descTxt= (TextView) findViewById(R.id.descDetailTxt);
        propTxt = (TextView) findViewById(R.id.propellantDetailTxt);

        //get intent
        Intent i=this.getIntent();

        //RECEIVE DATA
        String name = i.getExtras().getString("NAME_KEY");
        String desc = i.getExtras().getString("DESC_KEY");
        String propellant = i.getExtras().getString("PROP_KEY");


        Log.d(TAG, "aaaaaaaaaa   " +name);


        //BIND DATA
        nameTxt.setText(name);
        descTxt.setText(desc);
        propTxt.setText(propellant);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
