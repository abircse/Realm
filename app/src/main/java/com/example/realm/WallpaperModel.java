package com.example.realm;

import java.io.Serializable;

import io.realm.RealmObject;

public class WallpaperModel  extends RealmObject implements Serializable {


    private String title;
    private String price;
    private String details;
    private String imageurl;

    public WallpaperModel(String title, String price, String details, String imageurl) {
        this.title = title;
        this.price = price;
        this.details = details;
        this.imageurl = imageurl;
    }

    public WallpaperModel() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
