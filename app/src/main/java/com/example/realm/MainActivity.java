package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {


    private List<WallpaperModel> wallpaperModelList;
    private WalpaperAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        loadWallpaper();
        adapter = new WalpaperAdapter(wallpaperModelList,this);
        recyclerView.setAdapter(adapter);
    }

    private void loadWallpaper() {

        wallpaperModelList = new ArrayList<>();
        wallpaperModelList.add(new WallpaperModel("Android","166000","sndroid 4 Application Development","https://api.androidhive.info/images/realm/1.png"));
        wallpaperModelList.add(new WallpaperModel("JAVA","15000","Aandroid 4 d Development","https://api.androidhive.info/images/realm/2.png"));
        wallpaperModelList.add(new WallpaperModel("WEB","1000","Anrrdroid 4 Appeelication Development","https://api.androidhive.info/images/realm/3.png"));
        wallpaperModelList.add(new WallpaperModel("IOS","10030","Aendrwwoid 4 Applidfcation Development","https://api.androidhive.info/images/realm/4.png"));
        wallpaperModelList.add(new WallpaperModel("C#","1000","Androwidf 4 Application Development","https://api.androidhive.info/images/realm/5.png"));
        wallpaperModelList.add(new WallpaperModel("ASP.NET","1400","Androffid 4 Appeflication Development","https://api.androidhive.info/images/realm/6.png"));


    }
}
