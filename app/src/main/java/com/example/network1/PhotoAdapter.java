package com.example.network1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    List<Photo> photoList;

    public PhotoAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }
    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Photo Photo=photoList.get(position);
         holder.titleTextView.setText(Photo.getTitle());
         holder.idTextView.setText(String.valueOf(Photo.getId()));
         Picasso.get().load(Photo.getThumbnailUrl()).into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView idTextView;
        public TextView titleTextView;
        public ImageView thumbnailImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView= itemView.findViewById(R.id.tvtitle);
            thumbnailImageView=itemView.findViewById(R.id.imageV5);
            idTextView=itemView.findViewById(R.id.tvid);
        }
    }
}
