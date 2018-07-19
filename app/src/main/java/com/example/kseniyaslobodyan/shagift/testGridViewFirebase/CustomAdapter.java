package com.example.kseniyaslobodyan.shagift.testGridViewFirebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.Post;

import java.util.ArrayList;

/**
 * 1. where WE INFLATE OUR MODEL LAYOUT INTO VIEW ITEM
 * 2. THEN BIND DATA
 */
public class CustomAdapter extends BaseAdapter {
    private static final String TAG = "TAGA";
    Context c;
    ArrayList<Post> giftposts;

    public CustomAdapter(Context c, ArrayList<Post> giftposts) {
        this.c = c;
        this.giftposts = giftposts;
    }

    @Override
    public int getCount() {
        return giftposts.size();
    }

    @Override
    public Object getItem(int position) {
        return giftposts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
        TextView propTxt= (TextView) convertView.findViewById(R.id.propellantTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);

        final Post p = (Post) this.getItem(position);
        Log.d( TAG, "CCCCCCCCCC   " + p.getAuthorName() );

        nameTxt.setText(p.getAuthorName());
        propTxt.setText(p.getNamePost());
        descTxt.setText(p.getPostDescription());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity( p.getAuthorName(), p.getNamePost(), p.getImage());
            }
        });

        return convertView;
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c,DetailActivity.class);

        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("PROP_KEY",details[2]);

        c.startActivity(i);
    }
}