package com.theelitedevelopers.bunkies.ui.account_setup.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.theelitedevelopers.bunkies.databinding.ActivityPersonalHabitsBinding;

public class PersonalHabitsActivity extends AppCompatActivity {
    ActivityPersonalHabitsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalHabitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}