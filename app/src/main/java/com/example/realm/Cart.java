package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Cart extends AppCompatActivity {

    private Realm realm;
    private TextView Texttotalprice;
    private TextView noBookText;
    private Button checkout;
    private WallpaperModel wallpaperModel;

    private RecyclerView recyclerView;
    private List<WallpaperModel> wallpaperList;
    private CartAdapter cartAdapter;

    private String orderBookNames;
    private String orderedBookPrice;
    double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        realm = Realm.getDefaultInstance();

        Texttotalprice = findViewById(R.id.totalPrice);
        noBookText = findViewById(R.id.warning);
        checkout = findViewById(R.id.checkout);
        recyclerView = findViewById(R.id.cartrecyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        // load book  from cart
        wallpaperList = new ArrayList<>();
        wallpaperList= realm.where(WallpaperModel.class).findAll();

        // call for alltime Update price
        calculateTotalPrice();

        if (wallpaperList.isEmpty())
        {
            noBookText.setVisibility(View.VISIBLE);
        }
        else
        {
            noBookText.setVisibility(View.GONE);
        }

        // set to adapter
        cartAdapter = new CartAdapter(wallpaperList,this);
        recyclerView.setAdapter(cartAdapter);
        // adapter callback for delete item
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String objectID) {

                final RealmResults<WallpaperModel> realmResults= realm.where(WallpaperModel.class).equalTo("title",objectID).findAll();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realmResults.deleteAllFromRealm();
                        calculateTotalPrice();
                    }
                });

                cartAdapter.notifyDataSetChanged();
            }
        });

    }

    private void calculateTotalPrice() {


        List<WallpaperModel> allwallpaper= realm.where(WallpaperModel.class).findAll();

        if (allwallpaper.isEmpty())
        {
            noBookText.setVisibility(View.VISIBLE);
        }

        orderedBookPrice = "";
        totalPrice = 0;

        for(WallpaperModel w: allwallpaper){

            orderedBookPrice+=w.getPrice();
            totalPrice+= Double.valueOf(w.getPrice());
        }
        Texttotalprice.setText("Total: " + totalPrice);

    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}

