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
import com.theelitedevelopers.bunkies.databinding.ActivityPersonalInterestsBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.models.Interest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersonalInterestsActivity extends AppCompatActivity {
    ActivityPersonalInterestsBinding binding;
    ArrayList<Interest> interests = new ArrayList<>();
    ArrayList<Interest> selectedInterests = new ArrayList<>();
    int count;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInterestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        interests.add(new Interest("Music"));
        interests.add(new Interest("Photography"));
        interests.add(new Interest("Swimming"));
        interests.add(new Interest("Fashion"));
        interests.add(new Interest("Blogging"));
        interests.add(new Interest("Shopping"));
        interests.add(new Interest("Writing"));
        interests.add(new Interest("Sports"));
        interests.add(new Interest("Hiking"));
        interests.add(new Interest("Cooking"));
        interests.add(new Interest("Netflix and Chills"));
        interests.add(new Interest("Playing instruments"));
        interests.add(new Interest("Reading"));
        interests.add(new Interest("Taking strolls"));
        interests.add(new Interest("Making new friends"));
        interests.add(new Interest("Doing chores"));
        interests.add(new Interest("Acting"));

        addChips();

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.proceedButton.setOnClickListener(v -> {
            getSelectedChips();
        });

    }

    private void addChips(){
        for(Interest interest : interests){
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.custom_chip_layout, binding.chipGroup, false);
            chip.setText(interest.getName());
            binding.chipGroup.addView(chip);
        }
    }

    private void getSelectedChips(){
        selectedInterests.clear();
        count = binding.chipGroup.getChildCount();
        if(count > 0){
            int trackCount = 0;
            while(trackCount < count){
                Chip chip = (Chip) binding.chipGroup.getChildAt(trackCount);
                if(chip.isChecked()){
                    selectedInterests.add(new Interest(chip.getText().toString()));
                }
                trackCount++;
            }
            binding.progressBar.setVisibility(View.VISIBLE);
            saveInterestsToDB(selectedInterests);
        }else {
            Toast.makeText(PersonalInterestsActivity.this, "No interest was selected.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInterestsToDB(ArrayList<Interest> interests){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put("interests", interests);

        database.collection("roommates").document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    //If everything went successfully, then we need
                    //to tell Firebase that this user has finished the
                    //Traits section
                    markInterestsAsDone();
                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void markInterestsAsDone(){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put(Constants.PERSONAL_INTERESTS_DONE, true);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.getInstance(getApplicationContext()).saveBoolean(Constants.PERSONAL_INTERESTS_DONE, true);
                    startActivity(new Intent(PersonalInterestsActivity.this, PersonalHabitsActivity.class));

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(PersonalInterestsActivity.this, "Something happened. Try again.", Toast.LENGTH_SHORT).show();
                });
    }
}