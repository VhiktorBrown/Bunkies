package com.theelitedevelopers.bunkies.modules.main.ad.roommate_ad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.theelitedevelopers.bunkies.databinding.ActivityPostRoommateBinding;

public class PostRoommateActivity extends AppCompatActivity {
    ActivityPostRoommateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostRoommateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}