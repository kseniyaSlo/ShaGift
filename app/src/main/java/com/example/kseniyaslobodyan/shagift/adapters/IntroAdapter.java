package com.example.kseniyaslobodyan.shagift.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.DataIntro;

public class IntroAdapter extends FragmentPagerAdapter {

    DataIntro[] data = { new DataIntro("Lets talk about the elephant in the room", R.drawable.intro1),
                    new DataIntro("Do more, think less", R.drawable.intro2),
                    new DataIntro("Eat healthy", R.drawable.intro3)};

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