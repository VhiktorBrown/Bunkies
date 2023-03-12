package com.theelitedevelopers.bunkies.modules.account_setup.preferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.mohammedalaa.seekbar.DoubleValueSeekBarView;
import com.mohammedalaa.seekbar.OnDoubleValueSeekBarChangeListener;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityPreferencesBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.PersonalTraitsActivity;

import java.util.HashMap;
import java.util.Map;

public class PreferencesActivity extends AppCompatActivity {
    ActivityPreferencesBinding binding;
    int startAge=0, endAge=0;
    String maritalStatus="", genderPreference="",occupationalPreference="";
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

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
                startAge = i;
                endAge = i1;

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

        binding.maritalStatusRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            maritalStatus = button.getText().toString();
        });

        binding.genderRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            genderPreference = button.getText().toString();
        });

        binding.occupationRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            occupationalPreference = button.getText().toString();
        });

        binding.proceedButton.setOnClickListener(v -> {
            if(!maritalStatus.equals("") && !genderPreference.equals("")
            && !occupationalPreference.equals("")){
                if(startAge != 0 && endAge != 0){
                    binding.progressBar.setVisibility(View.VISIBLE);
                    //send Details to server
                    saveDetailsToDB();
                }else {
                    Toast.makeText(getApplicationContext(), "Select start and end age of roommate that you want.", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(PreferencesActivity.this, "Select Marriage, gender and occupational preference before you continue.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayToastAndEnableBar(){
        Toast.makeText(this, "The age range difference should not be less than 3", Toast.LENGTH_SHORT).show();
        binding.doubleRangeSeekbar.setEnabled(true);
    }

    private void saveDetailsToDB(){
        Map<String, Object> preferenceMap = new HashMap<>();
        preferenceMap.put("maritalPreference", maritalStatus);
        preferenceMap.put("genderPreference", genderPreference);
        preferenceMap.put("occupationalPreference", occupationalPreference);
        preferenceMap.put("startAge", String.valueOf(startAge));
        preferenceMap.put("endAge", String.valueOf(endAge));

        database.collection("roommates").document(currentUser.getUid())
                .set(preferenceMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    //If everything went successfully, then we need
                    //to tell Firebase that this user has finished the
                    //Preferences section
                    markPreferencesAsDone();

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void markPreferencesAsDone(){
        Map<String, Object> preferenceMap = new HashMap<>();
        preferenceMap.put(Constants.PREFERENCES_DONE, true);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(preferenceMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.getInstance(getApplicationContext()).saveBoolean(Constants.PREFERENCES_DONE, true);
                    startActivity(new Intent(PreferencesActivity.this, PersonalTraitsActivity.class));

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(PreferencesActivity.this, "Something happened. Try again.", Toast.LENGTH_SHORT).show();
                });
    }
}