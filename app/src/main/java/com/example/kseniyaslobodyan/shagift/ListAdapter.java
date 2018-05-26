package com.example.kseniyaslobodyan.shagift;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    List<GiftPost> giftPosts;

    private class ViewHolder{
        TextView userName;
        TextView whenGiftIsPost;
        ImageView userProfileImage;
        ImageView imgGift;
    }

    public ListAdapter(List<GiftPost> giftPosts) {
        this.giftPosts = giftPosts;
    }

    @Override
    public int getCount() {
        return giftPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_post_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.userName = convertView.findViewById(R.id.feed_post_name);
            viewHolder.whenGiftIsPost = convertView.findViewById(R.id.feed_post_when);
            viewHolder.userProfileImage = convertView.findViewById(R.id.feed_post_profile_img);
            viewHolder.imgGift = convertView.findViewById(R.id.feed_post_img);

            convertView.setTag(viewHolder);
        }

        GiftPost gp = giftPosts.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.userName.setText(gp.getNameGift());
        viewHolder.whenGiftIsPost.setText(gp.getWhenPosted());
        viewHolder.userProfileImage.setImageResource(gp.getProfileImage());
        viewHolder.imgGift.setImageResource(gp.getImageUrl());

        viewHolder.imgGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked on image in listPost", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
