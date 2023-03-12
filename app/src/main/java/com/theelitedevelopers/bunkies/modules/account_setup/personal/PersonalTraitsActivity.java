package com.theelitedevelopers.bunkies.modules.account_setup.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityPersonalTraitsBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.models.Trait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersonalTraitsActivity extends AppCompatActivity {
    ActivityPersonalTraitsBinding binding;
    ArrayList<Trait> traits = new ArrayList<>();
    int count;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

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
            binding.progressBar.setVisibility(View.VISIBLE);
            saveTraitsToDB(traits);
        }else {
            Toast.makeText(PersonalTraitsActivity.this, "No trait was selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveTraitsToDB(ArrayList<Trait> traits){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put("traits", traits);

        database.collection("roommates").document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    //If everything went successfully, then we need
                    //to tell Firebase that this user has finished the
                    //Traits section
                    markTraitsAsDone();

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void markTraitsAsDone(){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put(Constants.PERSONAL_TRAITS_DONE, true);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.getInstance(getApplicationContext()).saveBoolean(Constants.PERSONAL_TRAITS_DONE, true);
                    startActivity(new Intent(PersonalTraitsActivity.this, PersonalInterestsActivity.class));

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(PersonalTraitsActivity.this, "Something happened. Try again.", Toast.LENGTH_SHORT).show();
                });
    }
}