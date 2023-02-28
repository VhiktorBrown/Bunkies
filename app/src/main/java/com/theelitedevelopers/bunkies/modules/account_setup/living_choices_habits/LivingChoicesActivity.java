package com.theelitedevelopers.bunkies.modules.account_setup.living_choices_habits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mohammedalaa.seekbar.DoubleValueSeekBarView;
import com.mohammedalaa.seekbar.OnDoubleValueSeekBarChangeListener;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ActivityLivingChoicesBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.profile.SetupProfileActivity;

import java.text.NumberFormat;
import java.util.Locale;

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

        binding.doubleRangeSeekbar.setCurrentMinValue(100000);
        binding.doubleRangeSeekbar.setCurrentMinValue(150000);

        binding.doubleRangeSeekbar.setOnRangeSeekBarViewChangeListener(new OnDoubleValueSeekBarChangeListener() {
            @Override
            public void onValueChanged(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1, boolean b) {
                binding.startAge.setText(NumberFormat.getNumberInstance(Locale.US).format(i));
                binding.endAge.setText(NumberFormat.getNumberInstance(Locale.US).format(i1));

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
    }

    private void displayToastAndEnableBar(){
        Toast.makeText(this, "The age range difference should not be less than 3", Toast.LENGTH_SHORT).show();
        binding.doubleRangeSeekbar.setEnabled(true);
    }
}