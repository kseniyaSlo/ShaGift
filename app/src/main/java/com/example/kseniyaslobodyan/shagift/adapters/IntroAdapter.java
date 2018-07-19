package com.example.kseniyaslobodyan.shagift.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.DataIntro;

public class IntroAdapter extends FragmentPagerAdapter {

    DataIntro[] data = { new DataIntro("Welcome to Shagift", R.drawable.sec3 ),
                    new DataIntro("You can add gift post too", R.drawable.sec2 ),
                    new DataIntro("See profile page", R.drawable.sec1 )};

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return com.example.kseniyaslobodyan.shagift.fragments.IntroFragment.newInstance(data[position], position);
    }

    @Override
    public int getCount() {
        return 3;
    }

}