package com.theelitedevelopers.bunkies.modules.account_setup.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.theelitedevelopers.bunkies.databinding.ActivitySetupProfileBinding;
import com.theelitedevelopers.bunkies.modules.main.MainActivity;

public class SetupProfileActivity extends AppCompatActivity {
    ActivitySetupProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.proceedButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finishAffinity();
        });
    }
}