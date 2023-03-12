package com.theelitedevelopers.bunkies.modules.main.ad.room_ad;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.theelitedevelopers.bunkies.databinding.ActivityLocationRentAvailabilityBinding;

public class LocationRentAvailability extends AppCompatActivity {
    ActivityLocationRentAvailabilityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationRentAvailabilityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextButton.setOnClickListener(v -> {
            startActivity(new Intent(v.getContext(), RoomOverViewAttributesActivity.class));
        });
    }
}