package com.theelitedevelopers.bunkies.modules.main.ad.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.theelitedevelopers.bunkies.databinding.FragmentPostAnAdBinding;
import com.theelitedevelopers.bunkies.modules.main.ad.room_ad.LocationRentAvailability;
import com.theelitedevelopers.bunkies.modules.main.ad.roommate_ad.PostRoommateActivity;

public class PostAnAdFragment extends Fragment {
    FragmentPostAnAdBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostAnAdBinding.inflate(inflater, container, false);

        binding.roommateCardView.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), PostRoommateActivity.class));
        });

        binding.roomCardView.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), LocationRentAvailability.class));
        });
        return binding.getRoot();
    }
}