package com.example.catdatabase.model;

import androidx.room.PrimaryKey;

import java.util.List;

public class BreedPhotos {

    @PrimaryKey
    private String id;

    private String url;

    public List<Breeds> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Breeds> breeds) {
        this.breeds = breeds;
    }

    private List<Breeds> breeds;


    public String getId() {
        return id;
    }

    public  String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
