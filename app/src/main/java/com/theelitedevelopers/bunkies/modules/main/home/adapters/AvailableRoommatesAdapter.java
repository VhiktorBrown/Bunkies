package com.theelitedevelopers.bunkies.modules.main.home.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.AvailableRoommateLayoutBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;
import com.theelitedevelopers.bunkies.modules.main.roommate.RoommateDetailsActivity;

import java.util.ArrayList;

public class AvailableRoommatesAdapter extends RecyclerView.Adapter<AvailableRoommatesAdapter.AvailableRoommateViewHolder> {
    ArrayList<Roommate> availableRoommates;
    Context context;

    public AvailableRoommatesAdapter(Context context, ArrayList<Roommate> availableRoommates){
        this.context = context;
        this.availableRoommates = availableRoommates;
    }

    @NonNull
    @Override
    public AvailableRoommateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AvailableRoommateLayoutBinding binding = AvailableRoommateLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new AvailableRoommateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableRoommateViewHolder holder, int position) {
        Picasso.get()
                .load(availableRoommates.get(position).getImage())
                .placeholder(R.drawable.bunkies_onboarding_2)
                .into(holder.binding.roommateImageView);

        holder.binding.roommateName.setText("Richard Jameson, 23");
        holder.binding.roommateCityTime.setText("Awka, July 2023");
        holder.binding.roommateGenderWork.setText("Female|Student");

        holder.binding.budget.setText("$1,500/month");

        holder.binding.getRoot().setOnClickListener(v -> {
            v.getContext().startActivity(new Intent(v.getContext(), RoommateDetailsActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return availableRoommates.size();
    }

    public static class AvailableRoommateViewHolder extends RecyclerView.ViewHolder {

        AvailableRoommateLayoutBinding binding;

        public AvailableRoommateViewHolder(@NonNull AvailableRoommateLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
