package com.example.catdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catdatabase.model.Breeds;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchViewHolder> {
    public List<Breeds> breedList;

    public void setData(List<Breeds> breedList){
        this.breedList = breedList;
    }
    @NonNull
    @Override
    public SearchAdapter.searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.breed_item_view, parent, false);
        searchViewHolder searchViewHolder = new searchViewHolder(view);
        return searchViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.searchViewHolder holder, final int position) {
        final Breeds breedPos = breedList.get(position);

        holder.textView.setText(breedPos.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(context, BreedDetailActivity.class);
                intent.putExtra("BreedID", breedList.get(position).getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return breedList.size();
    }
    public static class searchViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView textView;

        public searchViewHolder(@NonNull View v) {
            super(v);
            view = v;
            textView = v.findViewById(R.id.textView);
        }
    }
}
