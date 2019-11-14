package com.example.catdatabase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catdatabase.BreedDetailActivity;
import com.example.catdatabase.FavAdapter;
import com.example.catdatabase.R;
import com.example.catdatabase.model.BreedPhotos;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<BreedPhotos> favList = BreedDetailActivity.dList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favs, container, false);
        recyclerView = view.findViewById(R.id.fav_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        FavAdapter favAdapter = new FavAdapter();
        favAdapter.setData(favList);
        recyclerView.setAdapter(favAdapter);
        return view;
    }

}
