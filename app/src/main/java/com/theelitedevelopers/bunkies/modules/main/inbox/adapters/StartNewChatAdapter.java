package com.theelitedevelopers.bunkies.modules.main.inbox.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.StartNewChatLayoutBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;
import com.theelitedevelopers.bunkies.modules.main.inbox.ChatActivity;

import java.util.ArrayList;

public class StartNewChatAdapter extends RecyclerView.Adapter<StartNewChatAdapter.StartNewChatViewHolder> {

    Context context;
    ArrayList<Roommate> roommates;

    public StartNewChatAdapter(Context context, ArrayList<Roommate> roommates){
        this.context = context;
        this.roommates = roommates;
    }

    @NonNull
    @Override
    public StartNewChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StartNewChatLayoutBinding binding = StartNewChatLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new StartNewChatViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StartNewChatViewHolder holder, int position) {

        holder.binding.inboxName.setText(roommates.get(holder.getAdapterPosition()).getName());
        holder.binding.gender.setText(roommates.get(holder.getAdapterPosition()).getGender());


            holder.binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);
                intent.putExtra(Constants.RECEIVER_UID, roommates.get(position).getUid());
                intent.putExtra(Constants.NAME, roommates.get(position).getName());
                v.getContext().startActivity(intent);
            });
    }

    public void setList(ArrayList<Roommate> roommates){
        this.roommates = roommates;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return roommates.size();
    }

    public static class StartNewChatViewHolder extends RecyclerView.ViewHolder {
        StartNewChatLayoutBinding binding;
        public StartNewChatViewHolder(@NonNull StartNewChatLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
