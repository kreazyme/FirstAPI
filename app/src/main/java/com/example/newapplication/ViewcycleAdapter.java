package com.example.newapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import model.DogLs;

public class ViewcycleAdapter extends RecyclerView.Adapter<ViewcycleAdapter.ViewHolder> {

    private List<DogLs> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ViewcycleAdapter(Context context, List<DogLs> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.dogcell, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtbread.setText(mData.get(position).getBred_for());
        holder.txtname.setText(mData.get(position).getName());
        Picasso.get()
                .load(mData.get(position).getUrl())
                .resize(200, 200)
                .centerCrop()
                .into(holder.image);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtname;
        TextView txtbread;
        ImageView image;
        ImageButton button;

        ViewHolder(View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.name);
            txtbread = itemView.findViewById(R.id.bread);
            image = itemView.findViewById(R.id.image);
            button = itemView.findViewById(R.id.tim);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}