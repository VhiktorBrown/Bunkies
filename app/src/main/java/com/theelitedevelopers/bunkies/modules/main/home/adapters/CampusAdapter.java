package com.theelitedevelopers.bunkies.modules.main.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ExploreCampusBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.RoomDetails;

import java.util.ArrayList;

public class CampusAdapter extends RecyclerView.Adapter<CampusAdapter.CampusViewHolder> {
    ArrayList<RoomDetails> rooms;
    Context context;

    public CampusAdapter(Context context, ArrayList<RoomDetails> rooms){
        this.context = context;
        this.rooms = rooms;
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
        Picasso.get()
                .load(rooms.get(position).getImage())
                .placeholder(R.drawable.bunkies_onboarding_1)
                .into(holder.binding.roomImageView);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class CampusViewHolder extends RecyclerView.ViewHolder {

        ExploreCampusBinding binding;

        public CampusViewHolder(@NonNull ExploreCampusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
