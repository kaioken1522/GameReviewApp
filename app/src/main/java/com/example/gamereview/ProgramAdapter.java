package com.example.gamereview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {

    Context context;
    String[] gameList;
    int[] imageList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowName;
        ImageView rowImage;

        public ViewHolder(@NonNull
        View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textView);
            rowImage = itemView.findViewById(R.id.imageView);
        }
    }


    public ProgramAdapter(Context context, String[] gameList, int [] imageList){
        this.context = context;
        this.gameList = gameList;
        this.imageList = imageList;

    }

    @NonNull
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ViewHolder holder, int position) {
     holder.rowName.setText(String.valueOf(gameList[position]));
     holder.rowImage.setImageResource(imageList[position]);



    }










    @Override
    public int getItemCount() {
        return gameList.length;
    }
}
