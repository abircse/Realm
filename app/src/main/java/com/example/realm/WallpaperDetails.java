package com.example.realm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.UUID;

import io.realm.Realm;

public class WallpaperDetails extends AppCompatActivity {

    private WallpaperModel wallpaperModel;
    private ImageView imageView;
    private TextView title, details;
    private Button button;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_details);

        realm = Realm.getDefaultInstance();
        imageView = findViewById(R.id.dimagview);
        title = findViewById(R.id.dtitle);
        details = findViewById(R.id.ddeatails);
        button = findViewById(R.id.addtocart);

        // receive complete model using percelable
        wallpaperModel= (WallpaperModel) getIntent().getSerializableExtra("wallpaperdata");

        // set data to view
        if (wallpaperModel != null) {

            title.setText(wallpaperModel.getTitle());
            details.setText(wallpaperModel.getDetails());
            Picasso.get().load(wallpaperModel.getImageurl()).into(imageView);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {


                        WallpaperModel wallpaper = realm.where(WallpaperModel.class).equalTo("title",wallpaperModel.getTitle()).findFirst();
                        if (wallpaper == null)
                        {
                            Toast.makeText(WallpaperDetails.this, "This wallper already Added to Cart", Toast.LENGTH_SHORT).show();
                        }


                        WallpaperModel model = realm.createObject(WallpaperModel.class);
                            model.setTitle(wallpaperModel.getTitle());
                            model.setPrice(wallpaperModel.getPrice());
                            model.setDetails(wallpaperModel.getDetails());
                            model.setImageurl(wallpaperModel.getImageurl());
                            Toast.makeText(WallpaperDetails.this, "Book Added To Cart", Toast.LENGTH_SHORT).show();




                    }
                });


            }
        });
    }

    public void gotocart(View view) {

        startActivity(new Intent(WallpaperDetails.this, Cart.class));
    }
}
