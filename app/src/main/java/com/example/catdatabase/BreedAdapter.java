package com.example.catdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.catdatabase.model.Breeds;

import java.util.ArrayList;
import java.util.List;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.BreedViewHolder> {
    private List<Breeds> breedsToAdapt;

    public void setData(List<Breeds> breedsToAdapt){
        this.breedsToAdapt = breedsToAdapt;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breed_item_view, parent, false);
        BreedViewHolder breedViewHolder = new BreedViewHolder(view);
        return breedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        final Breeds breedAtPosition = breedsToAdapt.get(position);
        holder.name.setText(breedAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BreedDetailActivity.class);
                intent.putExtra("BreedID", breedAtPosition.getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return breedsToAdapt.size();
    }
    public static class BreedViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView name;

        public BreedViewHolder(View v){
            super(v);
            view =v;
            name = v.findViewById(R.id.textView);
        }
    }

}
