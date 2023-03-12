package com.theelitedevelopers.bunkies.modules.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivitySplashscreenBinding;
import com.theelitedevelopers.bunkies.modules.authentication.LoginActivity;
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
            Intent intent;
            if(SharedPref.getInstance(getApplicationContext()).getBoolean(Constants.HAS_BEEN_LAUNCHED)){
                intent = new Intent(this, LoginActivity.class);
            }else {
                intent = new Intent(this, OnBoardingActivity.class);
            }
            startActivity(intent);
            finish();
        }, 500);
    }
}