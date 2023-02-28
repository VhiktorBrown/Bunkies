package com.theelitedevelopers.bunkies.ui.account_setup.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ActivityPersonalInterestsBinding;
import com.theelitedevelopers.bunkies.ui.account_setup.personal.models.Trait;

import java.util.ArrayList;

public class PersonalInterestsActivity extends AppCompatActivity {
    ActivityPersonalInterestsBinding binding;
    ArrayList<Trait> traits = new ArrayList<>();
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInterestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        traits.add(new Trait("Music"));
        traits.add(new Trait("Photography"));
        traits.add(new Trait("Swimming"));
        traits.add(new Trait("Fashion"));
        traits.add(new Trait("Blogging"));
        traits.add(new Trait("Shopping"));
        traits.add(new Trait("Writing"));
        traits.add(new Trait("Sports"));
        traits.add(new Trait("Hiking"));
        traits.add(new Trait("Cooking"));
        traits.add(new Trait("Netflix and Chills"));
        traits.add(new Trait("Playing instruments"));
        traits.add(new Trait("Reading"));
        traits.add(new Trait("Taking strolls"));
        traits.add(new Trait("Making new friends"));
        traits.add(new Trait("Doing chores"));
        traits.add(new Trait("Acting"));

        addChips();

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.proceedButton.setOnClickListener(v -> {
            getSelectedChips();
            startActivity(new Intent(this, PersonalHabitsActivity.class));
        });

    }

    private void addChips(){
        for(Trait trait : traits){
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.custom_chip_layout, binding.chipGroup, false);
            chip.setText(trait.getTraitName());
            binding.chipGroup.addView(chip);
        }
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