package com.theelitedevelopers.bunkies.modules.account_setup.living_choices_habits;

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
import com.theelitedevelopers.bunkies.databinding.ActivityLivingHabitsBinding;

import java.util.HashMap;
import java.util.Map;

public class LivingHabitsActivity extends AppCompatActivity {
    ActivityLivingHabitsBinding binding;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String cleanRoom="", haveVisitors="", stayHowLong="", smokingAcceptable="", foodChoice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLivingHabitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.cleanYourRoomRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            cleanRoom = button.getText().toString();
        });

        binding.visitorsRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            haveVisitors = button.getText().toString();
        });

        binding.visitorsHowLongRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            stayHowLong = button.getText().toString();
        });

        binding.smokeRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            smokingAcceptable = button.getText().toString();
        });

        binding.foodChoiceRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            foodChoice = button.getText().toString();
        });

        binding.proceedButton.setOnClickListener(v -> {
            if(!cleanRoom.equals("") && !haveVisitors.equals("") &&
                    !stayHowLong.equals("") && !smokingAcceptable.equals("") && !foodChoice.equals("")){
                binding.progressBar.setVisibility(View.VISIBLE);
                saveLivingHabitsToDB();
            }else {
                Toast.makeText(LivingHabitsActivity.this, "Please, select all buttons before you proceed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveLivingHabitsToDB(){
        Map<String, Object> habitsMap = new HashMap<>();
        habitsMap.put(Constants.LIVING_HABIT_CLEAN_ROOM, cleanRoom);
        habitsMap.put(Constants.LIVING_HABIT_HAVE_VISITORS, haveVisitors);
        habitsMap.put(Constants.LIVING_HABIT_STAY_HOW_LONG, stayHowLong);
        habitsMap.put(Constants.LIVING_HABIT_SMOKING_ACCEPTABLE, smokingAcceptable);
        habitsMap.put(Constants.LIVING_HABIT_FOOD_CHOICE, foodChoice);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(habitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    //If everything went successfully, then we need
                    //to tell Firebase that this user has finished the
                    //Traits section
                    markLivingHabitsAsDone();
                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void markLivingHabitsAsDone(){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put(Constants.LIVING_HABITS_DONE, true);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.getInstance(getApplicationContext()).saveBoolean(Constants.LIVING_HABITS_DONE, true);
                    startActivity(new Intent(LivingHabitsActivity.this, LivingChoicesActivity.class));

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(LivingHabitsActivity.this, "Something happened. Try again.", Toast.LENGTH_SHORT).show();
                });
    }
}