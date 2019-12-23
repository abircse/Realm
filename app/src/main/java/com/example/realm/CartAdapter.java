package com.example.realm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {

    private List<WallpaperModel> wallpapers;
    private Context context;
    private OnItemClickListener mListener;

    ///-------interface---------//
    public interface OnItemClickListener{
        void OnItemClick(String objectID);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }

    public CartAdapter(List<WallpaperModel> wallpapers, Context context) {
        this.wallpapers = wallpapers;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cartitem, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.wallpaperName.setText(wallpapers.get(position).getTitle());
        holder.wallpaperPrice.setText(wallpapers.get(position).getPrice());
        Picasso.get().load(wallpapers.get(position).getImageurl()).into(holder.wallpaperImage);


    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        Realm realm;
        TextView wallpaperName,wallpaperPrice, orderDate;
        ImageView wallpaperImage;
        ImageView wallpaperDelete;

        Holder(@NonNull View itemView) {
            super(itemView);
            realm= Realm.getDefaultInstance();

            wallpaperImage = itemView.findViewById(R.id.cartimage);
            wallpaperName = itemView.findViewById(R.id.cartname);
            wallpaperPrice = itemView.findViewById(R.id.cartprice);
            wallpaperDelete = itemView.findViewById(R.id.cartdelete);
            orderDate = itemView.findViewById(R.id.cartdate);
            wallpaperDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String id= wallpapers.get(getAdapterPosition()).getTitle();
                    mListener.OnItemClick(id);
                }
            });


        }
    }

}
