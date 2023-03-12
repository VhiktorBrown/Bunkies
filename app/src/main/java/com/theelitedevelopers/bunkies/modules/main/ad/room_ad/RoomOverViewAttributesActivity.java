package com.theelitedevelopers.bunkies.modules.main.ad.room_ad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.theelitedevelopers.bunkies.databinding.ActivityRoomOverViewAttributesBinding;

public class RoomOverViewAttributesActivity extends AppCompatActivity {
    ActivityRoomOverViewAttributesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomOverViewAttributesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}