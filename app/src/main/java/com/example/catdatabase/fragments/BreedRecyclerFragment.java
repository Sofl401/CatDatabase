package com.example.catdatabase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catdatabase.BreedAdapter;
import com.example.catdatabase.R;
import com.example.catdatabase.model.Breeds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BreedRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;

    public BreedRecyclerFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_recycler, container, false);
        recyclerView = view.findViewById(R.id.cat_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final BreedAdapter breedAdapter = new BreedAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        String url = "https://api.thecatapi.com/v1/breeds?api_key="+getString(R.string.cat_api_key);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<ArrayList<Breeds>>(){}.getType();
                ArrayList<Breeds> results = new Gson().fromJson(response, listType);
                breedAdapter.setData(results);
                recyclerView.setAdapter(breedAdapter);
                requestQueue.stop();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);



     return view;
    }

    }

