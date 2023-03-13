package com.theelitedevelopers.bunkies.modules.authentication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.databinding.ActivityRegisterBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.preferences.PreferencesActivity;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpButton.setOnClickListener(v -> {
            String fullName = binding.fullName.getText().toString();
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();

            if(fullName.length() > 1 && email.length() > 5 && fullName.length() > 5 &&
                    password.length() > 5) {
                Roommate roommate = new Roommate();
                roommate.setName(fullName);
                roommate.setEmail(email);
                roommate.setPassword(password);

                binding.progressBar.setVisibility(View.VISIBLE);
                registerNewRoommate(roommate);
            }
        });

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

        spannableString.setSpan(clickableSpan, 25, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.alreadyHaveAnAccount.setText(spannableString);
        binding.alreadyHaveAnAccount.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void registerNewRoommate(Roommate roommate){
        firebaseAuth.createUserWithEmailAndPassword(roommate.getEmail(), roommate.getPassword())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        Toast.makeText(RegisterActivity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                        saveRoommateDetailsToDB(roommate, user);
                    } else {
                        binding.progressBar.setVisibility(View.GONE);

                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveRoommateDetailsToDB(Roommate roommate, FirebaseUser firebaseUser){
        roommate.setUid(firebaseUser.getUid());

        Map<String, Object> roommateMap = new HashMap<>();
        roommateMap.put("name", roommate.getName());
        roommateMap.put("email", roommate.getEmail());
        roommateMap.put("password", roommate.getPassword());
        roommateMap.put("uid", roommate.getUid());


        // Add a new document with a generated ID
        database.collection("roommates")
                .document(firebaseUser.getUid())
                .set(roommateMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        binding.progressBar.setVisibility(View.GONE);
                        AppUtils.Companion.saveDataToSharedPref(RegisterActivity.this, roommate);

                        //move to Preferences Activity
                        startActivity(new Intent(RegisterActivity.this, PreferencesActivity.class));
                        finishAffinity();

                    }
                })
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }
}