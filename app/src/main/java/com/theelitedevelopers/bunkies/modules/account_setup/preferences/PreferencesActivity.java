package com.theelitedevelopers.bunkies.modules.account_setup.preferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mohammedalaa.seekbar.DoubleValueSeekBarView;
import com.mohammedalaa.seekbar.OnDoubleValueSeekBarChangeListener;
import com.theelitedevelopers.bunkies.databinding.ActivityPreferencesBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.PersonalTraitsActivity;

public class PreferencesActivity extends AppCompatActivity {
    ActivityPreferencesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.doubleRangeSeekbar.setCurrentMinValue(15);
        binding.doubleRangeSeekbar.setCurrentMinValue(45);

        binding.doubleRangeSeekbar.setOnRangeSeekBarViewChangeListener(new OnDoubleValueSeekBarChangeListener() {
            @Override
            public void onValueChanged(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1, boolean b) {
                binding.startAge.setText(i+"");
                binding.endAge.setText(i1+"");

                int diff = i1 - i;
                if(diff <= 3){
                    doubleValueSeekBarView.setEnabled(false);
                    displayToastAndEnableBar();
                }

            }

            @Override
            public void onStartTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {

            }

            @Override
            public void onStopTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {

            }
        });

        binding.proceedButton.setOnClickListener(v -> {
            startActivity(new Intent(this, PersonalTraitsActivity.class));
        });
    }

    private void displayToastAndEnableBar(){
        Toast.makeText(this, "The age range difference should not be less than 3", Toast.LENGTH_SHORT).show();
        binding.doubleRangeSeekbar.setEnabled(true);
    }
}