package com.example.catdatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.catdatabase.model.BreedPhotos;
import com.google.gson.Gson;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BreedDetailActivity extends AppCompatActivity {
    public static ArrayList<BreedPhotos> dList = new ArrayList<>();

    private TextView dog;
    private TextView name;
    private TextView description;
    private TextView origin;
    private TextView weight;
    private TextView temp;
    private TextView lifespan;
    private ImageView image;
    private Button fav;
    private Button wiki;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breed_detail_activity);

        dog = findViewById(R.id.dog);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        origin = findViewById(R.id.origin);
        weight = findViewById(R.id.weight);
        temp = findViewById(R.id.temp);
        lifespan = findViewById(R.id.lifespan);
        image = findViewById(R.id.image);
        fav = findViewById(R.id.fav);
        wiki = findViewById(R.id.wiki);

        Intent intent = getIntent();
        final String breedID = intent.getStringExtra("BreedID");
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + breedID;

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Gson gson = new Gson();
                BreedPhotos[] photoResponse = gson.fromJson(response, BreedPhotos[].class);
                final List<BreedPhotos> loadedCat = Arrays.asList(photoResponse);
                String imageUrl = loadedCat.get(0).getUrl();
                String imperial = loadedCat.get(0).getBreeds().get(0).getResult().getImperial();
                dog.setText("Dog Friendliness: " + String.valueOf(loadedCat.get(0).getBreeds().get(0).getDog_friendly()));
                name.setText(loadedCat.get(0).getBreeds().get(0).getName());
                description.setText(loadedCat.get(0).getBreeds().get(0).getDescription());
                origin.setText("Origin: " + loadedCat.get(0).getBreeds().get(0).getOrigin());
                temp.setText("Temperament: " + loadedCat.get(0).getBreeds().get(0).getTemperament());
                lifespan.setText("Lifespan: " + loadedCat.get(0).getBreeds().get(0).getLife_span() + "Years");

                if (imageUrl != null) {
                    Glide.with(getApplicationContext()).load(imageUrl).into(image);
                }

                weight.setText("Weight: " + imperial + " Kilograms");

                wiki.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = loadedCat.get(0).getBreeds().get(0).getWikipedia_url();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
                fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dList.add(loadedCat.get(0));
                        String name = loadedCat.get(0).getBreeds().get(0).getName();
                        Toast.makeText(getApplicationContext(), name + " has been added to favourites!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }
}
