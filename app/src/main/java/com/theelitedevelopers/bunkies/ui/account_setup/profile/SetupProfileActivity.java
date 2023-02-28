package com.theelitedevelopers.bunkies.ui.account_setup.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ActivitySetupProfileBinding;

public class SetupProfileActivity extends AppCompatActivity {
    ActivitySetupProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}