package com.theelitedevelopers.bunkies.ui.account_setup.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.theelitedevelopers.bunkies.databinding.ActivityPersonalInterestsBinding;

public class PersonalInterestsActivity extends AppCompatActivity {
    ActivityPersonalInterestsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInterestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}