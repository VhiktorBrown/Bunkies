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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityLoginBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.living_choices_habits.LivingChoicesActivity;
import com.theelitedevelopers.bunkies.modules.account_setup.living_choices_habits.LivingHabitsActivity;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.PersonalHabitsActivity;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.PersonalInterestsActivity;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.PersonalTraitsActivity;
import com.theelitedevelopers.bunkies.modules.account_setup.preferences.PreferencesActivity;
import com.theelitedevelopers.bunkies.modules.account_setup.profile.SetupProfileActivity;
import com.theelitedevelopers.bunkies.modules.main.MainActivity;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    Roommate roommate = new Roommate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.logInButton.setOnClickListener(v -> {

            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();

            if(email.length() > 0 && password.length() > 0){
                binding.progressBar.setVisibility(View.VISIBLE);

                Roommate roommate = new Roommate();
                roommate.setEmail(email);
                roommate.setPassword(password);

                loginStudent(roommate);
            }
        });

        SpannableString spannableString = new SpannableString("Don't have an account? Sign up");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#bf6429"));
                ds.setUnderlineText(true);
            }
        };

        spannableString.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.dontHaveAnAccount.setText(spannableString);
        binding.dontHaveAnAccount.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void loginStudent(Roommate roommate){

        firebaseAuth.signInWithEmailAndPassword(roommate.getEmail(), roommate.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            //fetch Roommate details from Database
                            getRoommateDetails(user.getUid());

                        } else {
                            binding.progressBar.setVisibility(View.GONE);

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getRoommateDetails(String uid){
        database.collection(Constants.ROOMMATES)
                .document(uid)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        roommate = task.getResult().toObject(Roommate.class);
                        if(roommate != null){
                            Toast.makeText(LoginActivity.this, "Fetched Roommate successfully.", Toast.LENGTH_SHORT).show();
                            checkRegistrationStatus(roommate);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    private void checkRegistrationStatus(Roommate roommate) {
        Toast.makeText(LoginActivity.this, String.valueOf(roommate.getPreferences_done()), Toast.LENGTH_SHORT).show();
        if (roommate.getPreferences_done() != null && roommate.getPreferences_done()) {
            if(roommate.getPersonal_traits_done() != null && roommate.getPersonal_traits_done()){
                if(roommate.getPersonal_interests_done() != null && roommate.getPersonal_interests_done()){
                    if(roommate.getPersonal_habits_done() != null && roommate.getPersonal_habits_done()){
                        if(roommate.getLiving_habits_done() != null && roommate.getLiving_habits_done()){
                            if(roommate.getLiving_choices_done() != null && roommate.getLiving_choices_done()){
                                if(roommate.getSetup_profile_done() != null && roommate.getSetup_profile_done()){
                                    startActivity(new Intent(this, MainActivity.class));
                                }else {
                                    startActivity(new Intent(this, SetupProfileActivity.class));
                                }
                            }else {
                                startActivity(new Intent(this, LivingChoicesActivity.class));
                            }
                        }else {
                            startActivity(new Intent(this, LivingHabitsActivity.class));
                        }
                    }else {
                        startActivity(new Intent(this, PersonalHabitsActivity.class));
                    }
                }else {
                    startActivity(new Intent(this, PersonalInterestsActivity.class));
                }
            }else {
                startActivity(new Intent(this, PersonalTraitsActivity.class));
            }
        }else {
            startActivity(new Intent(this, PreferencesActivity.class));
        }
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser != null){
            getRoommateDetails(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
    }
}