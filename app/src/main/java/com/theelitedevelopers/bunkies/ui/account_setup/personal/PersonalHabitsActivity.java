package com.theelitedevelopers.bunkies.ui.account_setup.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.theelitedevelopers.bunkies.databinding.ActivityPersonalHabitsBinding;
import com.theelitedevelopers.bunkies.ui.account_setup.living_choices_habits.LivingHabitsActivity;

public class PersonalHabitsActivity extends AppCompatActivity {
    ActivityPersonalHabitsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalHabitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.proceedButton.setOnClickListener( v -> {
            startActivity(new Intent(this, LivingHabitsActivity.class));
        });
    }
}