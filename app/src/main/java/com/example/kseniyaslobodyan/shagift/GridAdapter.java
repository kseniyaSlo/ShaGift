package com.example.kseniyaslobodyan.shagift;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private int[] images;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.grid_image);
        }
    }

    GridAdapter(Context context, int[] images) {

        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int resource = images[position];
        holder.img.setImageResource(resource);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked on image - move to list page", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}