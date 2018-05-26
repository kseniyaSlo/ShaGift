package com.example.kseniyaslobodyan.shagift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DashboardAdapter  extends BaseAdapter {

    private final Context mContext;
    private final GiftPost[] giftPosts;

    private class ViewHolder {
        //private final TextView nameTextView;
        //private final TextView authorTextView;
        // private final ImageView imageViewFavorite;

        ImageView imageViewCoverArt;

        private ViewHolder(ImageView imageViewCoverArt) {
            this.imageViewCoverArt = imageViewCoverArt;
        }

    }

    public DashboardAdapter(Context mContext, GiftPost[] giftPosts) {
        this.mContext = mContext;
        this.giftPosts = giftPosts;
    }

    @Override
    public int getCount() {
        return giftPosts.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GiftPost giftPost = giftPosts[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.image_gridview, null);
            final ImageView imageViewCoverArt = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
            final ViewHolder viewHolder = new ViewHolder(imageViewCoverArt);

            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();

        viewHolder.imageViewCoverArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked on Image in dashboard", Toast.LENGTH_LONG).show();
            }
        });

        //  viewHolder.nameTextView.setText(mContext.getString(giftPost.getNameGift()));
        // viewHolder.authorTextView.setText(mContext.getString(giftPost.getNameGift()));
        // viewHolder.imageViewFavorite.setImageResource(giftPost.getIsFavorite() ? R.drawable.star_enabled : R.drawable.star_disabled); /** for all the icons!!!**/
        Picasso.with(mContext).load(giftPost.getImageUrl()).into(viewHolder.imageViewCoverArt);

        return convertView;

    }


}
