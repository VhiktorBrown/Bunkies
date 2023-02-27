package com.theelitedevelopers.bunkies.ui.account_setup.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.theelitedevelopers.bunkies.databinding.ActivityPreferencesBinding;
import com.theelitedevelopers.bunkies.ui.account_setup.personal.PersonalTraitsActivity;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

public class PreferencesActivity extends AppCompatActivity {
    ActivityPreferencesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.proceedButton.setOnClickListener(v -> {
            startActivity(new Intent(this, PersonalTraitsActivity.class));
        });
    }
}