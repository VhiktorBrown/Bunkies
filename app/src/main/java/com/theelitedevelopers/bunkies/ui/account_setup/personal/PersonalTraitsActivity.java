package com.theelitedevelopers.bunkies.ui.account_setup.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.chip.Chip;
import com.theelitedevelopers.bunkies.R;
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

        traits.add(new Trait("Hardworking"));
        traits.add(new Trait("Peaceful"));
        traits.add(new Trait("Relaxed"));
        traits.add(new Trait("Clean"));
        traits.add(new Trait("Diligent"));
        traits.add(new Trait("Organised"));
        traits.add(new Trait("Communicative"));
        traits.add(new Trait("Active"));
        traits.add(new Trait("Sensitive"));
        traits.add(new Trait("Honest"));
        traits.add(new Trait("Responsible"));
        traits.add(new Trait("With a sense of humour"));
        traits.add(new Trait("Generous"));
        traits.add(new Trait("Creative"));
        traits.add(new Trait("Perceptive"));
        traits.add(new Trait("Tolerant"));
        traits.add(new Trait("Mature"));
        traits.add(new Trait("Fun"));
        traits.add(new Trait("Social"));


        addChips();

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.proceedButton.setOnClickListener(v -> {
            getSelectedChips();
            startActivity(new Intent(this, PersonalInterestsActivity.class));
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