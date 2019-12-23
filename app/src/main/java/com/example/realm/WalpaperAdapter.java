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

public class WalpaperAdapter extends RecyclerView.Adapter<WalpaperAdapter.Holder> {

    private List<WallpaperModel> wallpapers;
    private Context context;

    public WalpaperAdapter(List<WallpaperModel> wallpapers, Context context) {
        this.wallpapers = wallpapers;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_wallpaper, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.wallpaperName.setText(wallpapers.get(position).getTitle());
        Picasso.get().load(wallpapers.get(position).getImageurl()).into(holder.wallpaperImage);


    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView wallpaperName;
        ImageView wallpaperImage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            wallpaperImage = itemView.findViewById(R.id.wallpaperImage);
            wallpaperName = itemView.findViewById(R.id.wallpaperName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position= getAdapterPosition();
                    WallpaperModel wallpaper= wallpapers.get(position);
                    Intent i= new Intent(context, WallpaperDetails.class);
                    i.putExtra("wallpaperdata",wallpaper);
                    context.startActivity(i);

                }
            });


        }
    }

}
