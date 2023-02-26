package com.theelitedevelopers.bunkies.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;

import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.databinding.ActivityRegisterBinding;
import com.theelitedevelopers.bunkies.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        SpannableString spannableString = new SpannableString("Already have an account? Log in");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#bf6429"));
                ds.setUnderlineText(true);
            }
        };

        spannableString.setSpan(clickableSpan, 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.alreadyHaveAnAccount.setText(spannableString);
        binding.alreadyHaveAnAccount.setMovementMethod(LinkMovementMethod.getInstance());
    }
}