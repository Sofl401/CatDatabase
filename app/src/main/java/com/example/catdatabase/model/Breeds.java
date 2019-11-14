package com.example.catdatabase.model;

import androidx.annotation.NonNull;

public class Breeds {

    @NonNull
    private String id;
    private String name;
    private String temperament;

    public weight getResult() {
        return weight;
    }

    public void setResult(weight weight) {
        this.weight = weight;
    }

    private weight weight;
    private String life_span;
    private String alt_names;
    private String wikipedia_url;
    private String origin;

    private String description;
    private int dog_friendly;


    public class weight {
        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperical(String imperical) {
            this.imperial = imperical;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getAlt_names() {
        return alt_names;
    }

    public void setAlt_names(String alt_names) {
        this.alt_names = alt_names;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDog_friendly() {
        return dog_friendly;
    }

    public void setDog_friendly(int dog_friendly) {
        this.dog_friendly = dog_friendly;
    }
}