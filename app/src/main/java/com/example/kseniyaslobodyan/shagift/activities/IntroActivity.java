package com.example.kseniyaslobodyan.shagift.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.adapters.IntroAdapter;


public class IntroActivity extends AppCompatActivity {

    ViewPager introPager;
    TabLayout dots;
    Button button;

    final String SHOW_KEY = "show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //if (isFirstTime()) {
        introPager = (ViewPager) findViewById(R.id.pager);
        introPager.setAdapter(new IntroAdapter(getSupportFragmentManager()));
        introPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

                @Override
                public void onPageSelected(int position) {
                    if (position == 2) {
                        button.setText(R.string.ok);
                    } else {
                        button.setText(R.string.skip);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) { }
            });

            dots = (TabLayout) findViewById(R.id.dots);
            dots.setupWithViewPager(introPager, true);

            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences p = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = p.edit();
                    editor.putBoolean(SHOW_KEY, false);
                    editor.commit();

                    Intent intent = new Intent();
                    intent.setClass(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        //}
        /*else{
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            finish();
        }*/
    }

    private boolean isFirstTime() {
        SharedPreferences p = getPreferences(MODE_PRIVATE);

        return p.getBoolean(SHOW_KEY, true);
    }
}
