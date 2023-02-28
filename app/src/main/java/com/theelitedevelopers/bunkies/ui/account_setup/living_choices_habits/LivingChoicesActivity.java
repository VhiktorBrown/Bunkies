package com.theelitedevelopers.bunkies.ui.account_setup.living_choices_habits;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ActivityLivingChoicesBinding;
import com.theelitedevelopers.bunkies.ui.account_setup.profile.SetupProfileActivity;

public class LivingChoicesActivity extends AppCompatActivity {
    ActivityLivingChoicesBinding binding;
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
            startActivity(new Intent(this, SetupProfileActivity.class));
        });
    }
}