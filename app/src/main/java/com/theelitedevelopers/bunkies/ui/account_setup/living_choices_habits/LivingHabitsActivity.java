package com.theelitedevelopers.bunkies.ui.account_setup.living_choices_habits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.theelitedevelopers.bunkies.databinding.ActivityLivingHabitsBinding;

public class LivingHabitsActivity extends AppCompatActivity {
    ActivityLivingHabitsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLivingHabitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.proceedButton.setOnClickListener(v -> {
            startActivity(new Intent(this, LivingChoicesActivity.class));
        });
    }
}