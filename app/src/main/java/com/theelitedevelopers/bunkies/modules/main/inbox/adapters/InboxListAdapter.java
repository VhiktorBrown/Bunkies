package com.theelitedevelopers.bunkies.modules.main.inbox.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.InboxLayoutBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.Inbox;

import java.util.ArrayList;

public class InboxListAdapter extends RecyclerView.Adapter<InboxListAdapter.InboxViewHolder> {

    Context context;
    ArrayList<Inbox> inboxArrayList;

    public InboxListAdapter(Context context, ArrayList<Inbox> inboxArrayList){
        this.context = context;
        this.inboxArrayList = inboxArrayList;
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InboxLayoutBinding binding = InboxLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new InboxViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder holder, int position) {

        holder.binding.inboxName.setText(inboxArrayList.get(holder.getAdapterPosition()).getName());

        //set the Last message sent.
        if(inboxArrayList.get(holder.getAdapterPosition()).getLastMessage() != null){
            holder.binding.lastMessage.setText(inboxArrayList.get(holder.getAdapterPosition()).getLastMessage());
        }else {
            holder.binding.lastMessage.setText("");
        }

        holder.binding.date.setText("10:14 PM");
        //set Date.
        //holder.binding.date.setText(AppUtils.getBuzzDate(buzzArrayList.get(holder.getAdapterPosition()).getTime()));

            Picasso.get()
                    .load(inboxArrayList.get(position).getImage())
                    .placeholder(R.drawable.bunkies_onboarding_1)
                    .into(holder.binding.inboxImage);
    }

    public void setList(ArrayList<Inbox> inboxArrayList){
        this.inboxArrayList = inboxArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return inboxArrayList.size();
    }

    public static class InboxViewHolder extends RecyclerView.ViewHolder {
        InboxLayoutBinding binding;
        public InboxViewHolder(@NonNull InboxLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
