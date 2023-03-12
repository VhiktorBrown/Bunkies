package com.theelitedevelopers.bunkies.modules.account_setup.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityPersonalHabitsBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.living_choices_habits.LivingHabitsActivity;

import java.util.HashMap;
import java.util.Map;

public class PersonalHabitsActivity extends AppCompatActivity {
    ActivityPersonalHabitsBinding binding;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String cleanFreak="", partyAnimal="", pets="", smoking="", guests="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalHabitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.proceedButton.setOnClickListener( v -> {

            if(!cleanFreak.equals("") && !partyAnimal.equals("") &&
            !pets.equals("") && !smoking.equals("") && !guests.equals("")){
                binding.progressBar.setVisibility(View.VISIBLE);
                saveHabitsToDB();
            }else {
                Toast.makeText(PersonalHabitsActivity.this, "Please, select all buttons before you proceed.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.cleanFreakRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            cleanFreak = button.getText().toString();
        });

        binding.partyAnimalRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            partyAnimal = button.getText().toString();
        });

        binding.petsRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            pets = button.getText().toString();
        });

        binding.smokingRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            smoking = button.getText().toString();
        });

        binding.guestsRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            guests = button.getText().toString();
        });

    }

    private void saveHabitsToDB(){
        Map<String, Object> habitsMap = new HashMap<>();
        habitsMap.put(Constants.HABIT_CLEAN_FREAK, cleanFreak);
        habitsMap.put(Constants.HABIT_PARTY_ANIMAL, partyAnimal);
        habitsMap.put(Constants.HABIT_SMOKING, smoking);
        habitsMap.put(Constants.HABIT_PETS, pets);
        habitsMap.put(Constants.HABIT_LOTS_OF_GUESTS, guests);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(habitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    //If everything went successfully, then we need
                    //to tell Firebase that this user has finished the
                    //Traits section
                    markHabitsAsDone();
                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void markHabitsAsDone(){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put(Constants.PERSONAL_HABITS_DONE, true);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.getInstance(getApplicationContext()).saveBoolean(Constants.PERSONAL_HABITS_DONE, true);
                    startActivity(new Intent(PersonalHabitsActivity.this, LivingHabitsActivity.class));

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(PersonalHabitsActivity.this, "Something happened. Try again.", Toast.LENGTH_SHORT).show();
                });
    }
}