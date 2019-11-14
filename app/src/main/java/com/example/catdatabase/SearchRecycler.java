package com.example.catdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catdatabase.model.Breeds;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchRecycler extends AppCompatActivity {

    private TextView textView;
    private RecyclerView recyclerView;
    public static String result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        result = intent.getStringExtra("query");
        String url = " https://api.thecatapi.com/v1/breeds/search?q=" + result;
        recyclerView = findViewById(R.id.result_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Gson gson = new Gson();
                Breeds[] enums = gson.fromJson(response, Breeds[].class);
                List<Breeds> objectList = Arrays.asList(enums);
                Intent intent = getIntent();

                System.out.println(intent.getStringExtra("query"));
                SearchAdapter searchAdapter = new SearchAdapter();
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.setData(objectList);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);

        requestQueue.add(stringRequest);

    }
}