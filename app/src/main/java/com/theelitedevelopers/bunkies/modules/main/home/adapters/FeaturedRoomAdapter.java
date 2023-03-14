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
import com.theelitedevelopers.bunkies.databinding.AvailableRoomLayoutBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.RoomDetails;
import com.theelitedevelopers.bunkies.modules.main.room.RoomDetailsActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FeaturedRoomAdapter extends RecyclerView.Adapter<FeaturedRoomAdapter.FeaturedRoomViewHolder> {
    ArrayList<RoomDetails> rooms;
    Context context;
    int size =0;

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
                .placeholder(R.drawable.room)
                .into(holder.binding.roomImageView);

        holder.binding.roommateFacility1.setText("WiFi");
        holder.binding.roommateFacility2.setText("TV");
        holder.binding.roommateFacility3.setText("Elevator");

//        holder.binding.roomTime.setText("1 bedroom | immediately");
//        holder.binding.budget.setText("$1,500/month");
//        holder.binding.roomTypeCity.setText("1 bedroom, Awka");

        if(rooms.get(position).getRoomType() != null &&
                rooms.get(position).getNumberOfRooms() != null &&
                rooms.get(position).getCity() != null){
            holder.binding.roomTypeCity.setText(rooms.get(position).getNumberOfRooms()+
                    " "+rooms.get(position).getRoomType()+", "+rooms.get(position).getCity());
        }
        if(rooms.get(position).getNumberOfRooms() != null
        && rooms.get(position).getRoomType() != null){
            if(rooms.get(position).isImmediately()){
                holder.binding.roomTime.setText(rooms.get(position).getNumberOfRooms()+
                        " "+rooms.get(position).getRoomType()+" | immediately");
            }else {
                if(rooms.get(position).getDate() != null && !rooms.get(position).getDate().toString().equals("")){
                    holder.binding.roomTime.setText(rooms.get(position).getNumberOfRooms()+
                            " "+rooms.get(position).getRoomType()+" | "+ AppUtils.Companion.convertDateToPresentableFormatWithOnlyDate(
                            AppUtils.Companion.fromTimeStampToString(
                                    rooms.get(position).getDate().getSeconds()
                            )));
                }
            }
        }


        if(rooms.get(position).getRent() != null){
            holder.binding.budget.setText("NGN "+ NumberFormat.getNumberInstance(Locale.US).format(
                    Integer.parseInt(rooms.get(position).getRent()))+"/year");
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RoomDetailsActivity.class);
            intent.putExtra(Constants.ROOM_DETAILS, rooms.get(position));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(rooms.size() > 0){
            size = rooms.size();
        }
        return size;
    }

    public void setList(ArrayList<RoomDetails> rooms){
        this.rooms = rooms;
    }

    public static class FeaturedRoomViewHolder extends RecyclerView.ViewHolder {

        AvailableRoomLayoutBinding binding;

        public FeaturedRoomViewHolder(@NonNull AvailableRoomLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
