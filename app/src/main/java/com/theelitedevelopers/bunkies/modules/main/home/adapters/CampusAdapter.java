package com.theelitedevelopers.bunkies.modules.main.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theelitedevelopers.bunkies.databinding.ExploreCampusBinding;

import java.util.List;

public class CampusAdapter extends RecyclerView.Adapter<CampusAdapter.CampusViewHolder> {
    List<String> cities;
    Context context;

    public CampusAdapter(Context context, List<String> cities){
        this.context = context;
        this.cities = cities;
    }

    @NonNull
    @Override
    public CampusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExploreCampusBinding binding = ExploreCampusBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new CampusViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CampusViewHolder holder, int position) {
        holder.binding.nameOfCampus.setText(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class CampusViewHolder extends RecyclerView.ViewHolder {

        ExploreCampusBinding binding;

        public CampusViewHolder(@NonNull ExploreCampusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
