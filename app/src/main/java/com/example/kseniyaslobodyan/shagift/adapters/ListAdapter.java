package com.example.kseniyaslobodyan.shagift.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kseniyaslobodyan.shagift.model.GiftPost;
import com.example.kseniyaslobodyan.shagift.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    List<GiftPost> giftPosts;

    private class ViewHolder{
        TextView authorPost;
        TextView postName;
        TextView postDesc;
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
            viewHolder.authorPost = convertView.findViewById(R.id.feed_authorpost);
            viewHolder.postName = convertView.findViewById(R.id.feed_postnmame);
            viewHolder.postDesc = convertView.findViewById(R.id.feed_postdesc);
            viewHolder.imgGift = convertView.findViewById(R.id.feed_post_img);

            convertView.setTag(viewHolder);
        }

        GiftPost gp = giftPosts.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.authorPost.setText(gp.getNamePost());
        viewHolder.postName.setText(gp.getNamePost());
        viewHolder.postDesc.setText(gp.getDescPodt());
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
