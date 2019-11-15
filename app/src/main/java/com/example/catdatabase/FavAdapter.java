package com.example.catdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catdatabase.model.BreedPhotos;
import com.example.catdatabase.model.Breeds;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavAdapterViewHolder> {
    private static ArrayList<BreedPhotos> breedsList = new ArrayList<>();

    public void setData(ArrayList<BreedPhotos> breedsList) {
        this.breedsList = breedsList;
    }

    @NonNull
    @Override
    public FavAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breed_item_view, parent, false);
        FavAdapterViewHolder favAdapterViewHolder = new FavAdapterViewHolder(view);
        return favAdapterViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapterViewHolder holder, int position) {
        final String cat = breedsList.get(position).getBreeds().get(0).getId();
        holder.text.setText(breedsList.get(position).getBreeds().get(0).getName());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, BreedDetailActivity.class);
                    intent.putExtra("BreedID", cat);
                    context.startActivity(intent);
                }
            });
    }


    @Override
    public int getItemCount() {
        return breedsList.size();

    }

    public class FavAdapterViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView text;

        public FavAdapterViewHolder(@NonNull View v) {
            super(v);
            view = v;
            text = view.findViewById(R.id.textView);
        }
    }
}
