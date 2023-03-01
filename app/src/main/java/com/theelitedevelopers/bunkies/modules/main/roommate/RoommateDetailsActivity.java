package com.theelitedevelopers.bunkies.modules.main.roommate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ActivityRoommateDetailsBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.models.Interest;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.models.Trait;

import java.util.ArrayList;

public class RoommateDetailsActivity extends AppCompatActivity {
    ActivityRoommateDetailsBinding binding;
    ArrayList<Trait> traits = new ArrayList<>();
    ArrayList<Interest> interests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoommateDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        traits.add(new Trait("Hardworking"));
        traits.add(new Trait("Peaceful"));
        traits.add(new Trait("Relaxed"));
        traits.add(new Trait("Clean"));
        traits.add(new Trait("Diligent"));
        traits.add(new Trait("Organised"));
        traits.add(new Trait("Communicative"));

        interests.add(new Interest("Music"));
        interests.add(new Interest("Photography"));
        interests.add(new Interest("Swimming"));
        interests.add(new Interest("Fashion"));
        interests.add(new Interest("Blogging"));
        interests.add(new Interest("Shopping"));
        interests.add(new Interest("Writing"));

        addChips();

    }

    private void addChips(){
        for(Trait trait : traits){
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.custom_static_chip_layout, binding.personalityChipGroup, false);
            chip.setText(trait.getTraitName());
            binding.personalityChipGroup.addView(chip);
        }

        for(Interest interest : interests){
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.custom_static_chip_layout, binding.interestsChipGroup, false);
            chip.setText(interest.getName());
            binding.interestsChipGroup.addView(chip);
        }
    }

}