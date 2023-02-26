package com.theelitedevelopers.bunkies.ui.account_setup.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.theelitedevelopers.bunkies.databinding.ActivityPreferencesBinding;

public class PreferencesActivity extends AppCompatActivity {
    ActivityPreferencesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}