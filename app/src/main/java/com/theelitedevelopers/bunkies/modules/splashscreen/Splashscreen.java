package com.theelitedevelopers.bunkies.modules.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.theelitedevelopers.bunkies.databinding.ActivitySplashscreenBinding;
import com.theelitedevelopers.bunkies.modules.onboarding.OnBoardingActivity;

@SuppressLint("CustomSplashScreen")
public class Splashscreen extends AppCompatActivity {
    ActivitySplashscreenBinding binding;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }, 500);
    }
}