package com.theelitedevelopers.bunkies.modules.main.home.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.AvailableRoommateLayoutBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;
import com.theelitedevelopers.bunkies.modules.main.roommate.RoommateDetailsActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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

//        holder.binding.roommateName.setText("Richard Jameson, 23");
//        holder.binding.roommateCityTime.setText("Awka, July 2023");
//        holder.binding.roommateGenderWork.setText("Female|Student");
//
//        holder.binding.budget.setText("$1,500/month");
        holder.binding.roommateName.setText(availableRoommates.get(position).getName()+", 21");
        if(availableRoommates.get(position).isImmediately()){
            holder.binding.roommateCityTime.setText(availableRoommates.get(position).
                    getCity()+", Immediately");
        }else {
            holder.binding.roommateCityTime.setText(availableRoommates.get(position).
                    getCity()+", "+ AppUtils.Companion.convertDateToPresentableFormatWithOnlyDate(
                    AppUtils.Companion.fromTimeStampToString(
                            availableRoommates.get(position).getDate().getSeconds()
                    )));
        }

        holder.binding.roommateGenderWork.setText(availableRoommates.get(position).getGender()+
                " | "+availableRoommates.get(position).getOccupation());

        //set Budget
        holder.binding.budget.setText("NGN "+ NumberFormat.getNumberInstance(Locale.US).format(
                availableRoommates.get(position).getBudget())+"/year");


        holder.binding.getRoot().setOnClickListener(v -> {
            v.getContext().startActivity(
                    new Intent(v.getContext(), RoommateDetailsActivity.class)
                    .putExtra(Constants.ROOMMATE_DETAILS, availableRoommates.get(position)));
        });
    }

    public void setList(ArrayList<Roommate> roommates){
        this.availableRoommates = roommates;
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
