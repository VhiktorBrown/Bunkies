package com.theelitedevelopers.bunkies.modules.account_setup.living_choices_habits;

import android.annotation.SuppressLint;
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
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityLivingChoicesBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.profile.SetupProfileActivity;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LivingChoicesActivity extends AppCompatActivity {
    ActivityLivingChoicesBinding binding;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String pets="", shareAToilet="";
    int startRent=0, endRent=0, lookingFor=2;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLivingChoicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBack.setOnClickListener(v -> {
            onBackPressed();
        });



        binding.onePerson.setOnClickListener(v -> {
            lookingFor = 1;
            binding.onePerson.setBackground(getResources().getDrawable(R.drawable.selected_textview_background));
            binding.onePerson.setTextColor(getResources().getColor(R.color.secondary));

            //change the rest of the textView's backgrounds
            binding.twoPeople.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.twoPeople.setTextColor(getResources().getColor(R.color.black));

            binding.threePeople.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.threePeople.setTextColor(getResources().getColor(R.color.black));

            binding.fourAndMorePerson.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.fourAndMorePerson.setTextColor(getResources().getColor(R.color.black));
        });

        binding.twoPeople.setOnClickListener(v -> {
            lookingFor = 2;
            binding.twoPeople.setBackground(getResources().getDrawable(R.drawable.selected_textview_background));
            binding.twoPeople.setTextColor(getResources().getColor(R.color.secondary));

            //change the rest of the textView's backgrounds
            binding.onePerson.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.onePerson.setTextColor(getResources().getColor(R.color.black));

            binding.threePeople.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.threePeople.setTextColor(getResources().getColor(R.color.black));

            binding.fourAndMorePerson.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.fourAndMorePerson.setTextColor(getResources().getColor(R.color.black));
        });

        binding.threePeople.setOnClickListener(v -> {
            lookingFor = 3;
            binding.threePeople.setBackground(getResources().getDrawable(R.drawable.selected_textview_background));
            binding.threePeople.setTextColor(getResources().getColor(R.color.secondary));

            //change the rest of the textView's backgrounds
            binding.onePerson.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.onePerson.setTextColor(getResources().getColor(R.color.black));

            binding.twoPeople.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.twoPeople.setTextColor(getResources().getColor(R.color.black));

            binding.fourAndMorePerson.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.fourAndMorePerson.setTextColor(getResources().getColor(R.color.black));
        });

        binding.fourAndMorePerson.setOnClickListener(v -> {
            lookingFor = 4;
            binding.fourAndMorePerson.setBackground(getResources().getDrawable(R.drawable.selected_textview_background));
            binding.fourAndMorePerson.setTextColor(getResources().getColor(R.color.secondary));

            //change the rest of the textView's backgrounds
            binding.twoPeople.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.twoPeople.setTextColor(getResources().getColor(R.color.black));

            binding.threePeople.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.threePeople.setTextColor(getResources().getColor(R.color.black));

            binding.onePerson.setBackground(getResources().getDrawable(R.drawable.textview_background));
            binding.onePerson.setTextColor(getResources().getColor(R.color.black));
        });

        binding.proceedButton.setOnClickListener(v -> {
            if(startRent != 0 && endRent != 0 &&
            !pets.equals("") && !shareAToilet.equals("")){
                binding.progressBar.setVisibility(View.VISIBLE);
                saveLivingChoicesToDB();
            }else {
                Toast.makeText(LivingChoicesActivity.this, "Please, select all fields and buttons before you proceed.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.doubleRangeSeekbar.setCurrentMinValue(20000);
        binding.doubleRangeSeekbar.setCurrentMinValue(500000);

        binding.doubleRangeSeekbar.setOnRangeSeekBarViewChangeListener(new OnDoubleValueSeekBarChangeListener() {
            @Override
            public void onValueChanged(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1, boolean b) {
                binding.startAge.setText(NumberFormat.getNumberInstance(Locale.US).format(i));
                binding.endAge.setText(NumberFormat.getNumberInstance(Locale.US).format(i1));

                int diff = i1 - i;
                if(diff <= 5000){
                    doubleValueSeekBarView.setEnabled(false);
                    displayToastAndEnableBar();
                }

                startRent = i;
                endRent = i1;

            }

            @Override
            public void onStartTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {

            }

            @Override
            public void onStopTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {

            }
        });

            binding.petsRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            pets = button.getText().toString();
        });

        binding.shareAToiletRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton button = (RadioButton) findViewById(i);
            shareAToilet = button.getText().toString();
        });
    }

    private void displayToastAndEnableBar(){
        Toast.makeText(this, "The rent range difference should not be less than 5000", Toast.LENGTH_SHORT).show();
        binding.doubleRangeSeekbar.setEnabled(true);
    }

    private void saveLivingChoicesToDB(){
        Map<String, Object> habitsMap = new HashMap<>();
        habitsMap.put(Constants.LIVING_CHOICE_RENT_START, String.valueOf(startRent));
        habitsMap.put(Constants.LIVING_CHOICE_RENT_END, String.valueOf(endRent));
        habitsMap.put(Constants.LIVING_CHOICE_LOOKING_FOR, String.valueOf(lookingFor));
        habitsMap.put(Constants.LIVING_CHOICE_PETS, pets);
        habitsMap.put(Constants.LIVING_CHOICE_SHARE_TOILET, shareAToilet);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(habitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    //If everything went successfully, then we need
                    //to tell Firebase that this user has finished the
                    //Traits section
                    markLivingChoicesAsDone();
                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void markLivingChoicesAsDone(){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put(Constants.LIVING_CHOICES_DONE, true);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.getInstance(getApplicationContext()).saveBoolean(Constants.LIVING_CHOICES_DONE, true);
                    startActivity(new Intent(LivingChoicesActivity.this, SetupProfileActivity.class));

                })
                .addOnFailureListener(e -> {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(LivingChoicesActivity.this, "Something happened. Try again.", Toast.LENGTH_SHORT).show();
                });
    }
}