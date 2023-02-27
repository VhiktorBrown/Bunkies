package com.theelitedevelopers.bunkies.ui.account_setup.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.theelitedevelopers.bunkies.databinding.ActivityPersonalTraitsBinding;
import com.theelitedevelopers.bunkies.ui.account_setup.personal.models.Trait;

import java.util.ArrayList;

public class PersonalTraitsActivity extends AppCompatActivity {
    ActivityPersonalTraitsBinding binding;
    ArrayList<Trait> traits = new ArrayList<>();
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalTraitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.proceedButton.setOnClickListener(v -> {
            getSelectedChips();
            startActivity(new Intent(this, PersonalInterestsActivity.class));
        });
    }

    private void addChips(){
        Chip chip = new Chip(this);
        
    }

    private void getSelectedChips(){
        count = binding.chipGroup.getChildCount();
        if(count > 0){
            int trackCount = 0;
            while(trackCount < count){
                Chip chip = (Chip) binding.chipGroup.getChildAt(trackCount);
                if(chip.isChecked()){
                    traits.add(new Trait(chip.getText().toString()));
                }
                trackCount++;
            }
        }
    }
}