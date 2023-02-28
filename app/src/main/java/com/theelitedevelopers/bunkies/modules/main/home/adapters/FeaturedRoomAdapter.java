package com.theelitedevelopers.bunkies.modules.main.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.AvailableRoomLayoutBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.RoomDetails;

import java.util.ArrayList;

public class FeaturedRoomAdapter extends RecyclerView.Adapter<FeaturedRoomAdapter.FeaturedRoomViewHolder> {
    ArrayList<RoomDetails> rooms;
    Context context;

    public FeaturedRoomAdapter(Context context, ArrayList<RoomDetails> rooms){
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public FeaturedRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AvailableRoomLayoutBinding binding = AvailableRoomLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new FeaturedRoomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedRoomViewHolder holder, int position) {
        Picasso.get()
                .load(rooms.get(position).getImage())
                .placeholder(R.drawable.bunkies_onboarding_1)
                .into(holder.binding.roomImageView);

        holder.binding.roommateFacility1.setText("WiFi");
        holder.binding.roommateFacility2.setText("TV");
        holder.binding.roommateFacility3.setText("Elevator");

        holder.binding.roomTime.setText("1 bedroom | immediately");
        holder.binding.budget.setText("$1,500/month");
        holder.binding.roomTypeCity.setText("1 bedroom, Awka");
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class FeaturedRoomViewHolder extends RecyclerView.ViewHolder {

        AvailableRoomLayoutBinding binding;

        public FeaturedRoomViewHolder(@NonNull AvailableRoomLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
