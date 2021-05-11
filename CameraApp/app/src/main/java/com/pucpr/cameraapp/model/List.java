package com.pucpr.cameraapp.model;

import android.media.Rating;
import android.widget.EditText;

public class List {
    private long id;
    private String name;
    private int valorm;
    private String path;
    private int rating;

    public List(long id, String name, int valorm, String path, int rating) {
        this.id = id;
        this.name = name;
        this.valorm = valorm;
        this.path = path;
        this.rating = rating;
    }

    public List(String name, int valorm, String path, int rating) {
        this.name = name;
        this.valorm = valorm;
        this.path = path;
        this.rating = rating;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getvalorm() {
        return valorm;
    }

    public void setvalorm(int valorm) {
        this.valorm = valorm;
    }
}
