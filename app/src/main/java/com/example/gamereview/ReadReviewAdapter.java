package com.example.gamereview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReadReviewAdapter extends RecyclerView.Adapter<ReadReviewAdapter.ViewHolder> {
    Context context;
    String[] description;
    int[] proflePhotoList;
    private ArrayList<ReadReviewsPageModal> readReviewsPageModalArrayList;
    private Bitmap bitmap;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowName;
        ImageView rowImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textView);
            rowImage = itemView.findViewById(R.id.imageView);
        }
    }

    public ReadReviewAdapter(Context context, ArrayList<ReadReviewsPageModal> readReviewsPageModalArrayList){
        this.context = context;
        this.readReviewsPageModalArrayList = readReviewsPageModalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_rrp, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReadReviewsPageModal readReviewsPageModal = readReviewsPageModalArrayList.get(position);
        holder.rowName.setText(readReviewsPageModal.getReview());
        bitmap = BitmapFactory.decodeByteArray(readReviewsPageModal.getProfilePic(), 0, readReviewsPageModal.getProfilePic().length);
        holder.rowImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return readReviewsPageModalArrayList.size();
    }
}

