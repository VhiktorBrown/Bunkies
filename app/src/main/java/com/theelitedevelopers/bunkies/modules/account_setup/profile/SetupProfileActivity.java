package com.theelitedevelopers.bunkies.modules.account_setup.profile;

import static com.theelitedevelopers.bunkies.core.utils.Constants.BIO;
import static com.theelitedevelopers.bunkies.core.utils.Constants.CITY;
import static com.theelitedevelopers.bunkies.core.utils.Constants.GENDER;
import static com.theelitedevelopers.bunkies.core.utils.Constants.NEIGHBOURHOOD;
import static com.theelitedevelopers.bunkies.core.utils.Constants.OCCUPATION;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivitySetupProfileBinding;
import com.theelitedevelopers.bunkies.databinding.DropDownBottomSheetLayoutBinding;
import com.theelitedevelopers.bunkies.modules.main.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SetupProfileActivity extends AppCompatActivity {
    ActivitySetupProfileBinding binding;
    String fullName="", bio="";
    String gender="Select Gender", city="Select City", neighbourhood="Select Neighbourhood", occupation="Select Occupation";
    Calendar dateOfBirth = Calendar.getInstance();
    String date="";
    Timestamp dateOfBirthTimeStamp;
    SimpleDateFormat simpleDateFormat, simpleTimeFormat;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.gender.setOnClickListener(v -> {
            showBottomSheetDialog(binding.gender.getId());
        });

        binding.city.setOnClickListener(v -> {
            showBottomSheetDialog(binding.city.getId());
        });

        binding.neighbourhood.setOnClickListener(v -> {
            showBottomSheetDialog(binding.neighbourhood.getId());
        });

        binding.occupation.setOnClickListener(v -> {
            showBottomSheetDialog(binding.occupation.getId());
        });

        binding.dateOfBirth.setOnClickListener(v -> {
            showDateTimePicker();
        });

        binding.proceedButton.setOnClickListener(v -> {
            if(validateUserInput()){
                binding.progressBar.setVisibility(View.VISIBLE);
                saveProfileToDB();
            }
        });
    }

    private boolean validateUserInput() {
        fullName = binding.fullName.getText().toString();
        bio = binding.bio.getText().toString();
        if (fullName.isEmpty()) {
            AppUtils.Companion.showSnackBar(binding.getRoot(), "Please enter your Full Name");
            return false;
        }
        if (bio.isEmpty() || bio.length() < 10) {
            AppUtils.Companion.showSnackBar(binding.getRoot(), "Your bio is too short");
            return false;
        }
        if (gender.equals("Select Gender")) {
            AppUtils.Companion.showSnackBar(binding.getRoot(), "Please select your Gender.");
            return false;
        }
        if (city.equals("Select City")) {
            AppUtils.Companion.showSnackBar(binding.getRoot(), "Please select your City");
            return false;
        }
        if (neighbourhood.equals("Select Neighbourhood")) {
            AppUtils.Companion.showSnackBar(binding.getRoot(), "Please select your Neighbourhood");
            return false;
        }
        if (occupation.equals("Select Occupation")) {
            AppUtils.Companion.showSnackBar(binding.getRoot(), "Please select your Occupation");
            return false;
        }
        if(date.equals("")){
            AppUtils.Companion.showSnackBar(binding.getRoot(), "Please select your Date of Birth");
            return false;
        }
        return true;
    }

    private void showBottomSheetDialog(int id) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        DropDownBottomSheetLayoutBinding dialogBinding
                = DropDownBottomSheetLayoutBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(dialogBinding.getRoot());
        bottomSheetDialog.show();

        String editTextName = getTextViewNameFromId(id);
        dialogBinding.title.setText(editTextName);


        dialogBinding.dropDownRv.setLayoutManager(new LinearLayoutManager(this));
        dialogBinding.dropDownRv.setHasFixedSize(true);


        BottomSheetDialogRvAdapter bottomSheetDialogRvAdapter = new BottomSheetDialogRvAdapter(getListOfItemsForBottomSheet(editTextName));
        bottomSheetDialogRvAdapter.setOnItemCLickListener(item -> {
            switch (editTextName) {
                case GENDER: {
                    gender = item;
                    binding.gender.setText(gender);
                    bottomSheetDialog.dismiss();
                    break;
                }
                case CITY: {
                    city = item;
                    binding.city.setText(city);
                    bottomSheetDialog.dismiss();
                    break;
                }
                case NEIGHBOURHOOD: {
                    neighbourhood = item;
                    binding.neighbourhood.setText(neighbourhood);
                    bottomSheetDialog.dismiss();
                    break;
                }
                case OCCUPATION: {
                    occupation = item;
                    binding.occupation.setText(occupation);
                    bottomSheetDialog.dismiss();
                    break;
                }
            }
        });
        dialogBinding.dropDownRv.setAdapter(bottomSheetDialogRvAdapter);
    }

    private List<String> getListOfItemsForBottomSheet(String items) {
        switch (items) {
            case GENDER: {
                return setUpGenders();
            }
            case CITY: {
                return setUpCities();
            }
            case NEIGHBOURHOOD: {
                return setUpLocations();
            }
            case OCCUPATION: {
                return setUpOccupations();
            }
            default: return Collections.emptyList();
        }
    }

    public void showDateTimePicker(){
        final Calendar currentDate = Calendar.getInstance();
        dateOfBirth = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            dateOfBirth.set(year, month, dayOfMonth);

            simpleDateFormat = new SimpleDateFormat("EEE dd MMM yyyy");
            simpleTimeFormat = new SimpleDateFormat("hh:mm aa");

            String sourceFormat = "EEE MMM d HH:mm:ss z yyyy";
            String destinationFormat = "EEE, d MMM yyyy HH:mm:ss";

            //convert date to Universal format
            date = AppUtils.Companion.convertDateFromOneFormatToAnother(sourceFormat, destinationFormat, dateOfBirth.getTime().toString());
            binding.dateOfBirth.setText(simpleDateFormat.format(dateOfBirth.getTime()));

            try {
                getTimeStamp(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }

    private void getTimeStamp(String date) throws ParseException {
        String destinationFormat = "EEE, d MMM yyyy HH:mm:ss";

        dateOfBirthTimeStamp = new Timestamp(Objects.requireNonNull(
                AppUtils.Companion.convertToDateFormat(destinationFormat, date)));
    }

    private List<String> setUpGenders(){
        //There are only 2 Genders.
        String[] genders = {"Male", "Female"};
        return Arrays.asList(genders);
    }

    private List<String> setUpCities(){
        //There are only 2 Genders.
        String[] cities = {"Awka", "Enugu", "Lagos", "Kaduna", };
        return Arrays.asList(cities);
    }

    private List<String> setUpLocations(){
        //There are only 2 Genders.
        String[] locations = {"Ifite", "Agwu-Awka", "Ngozika Estate", "Aroma", "Term-site"};
        return Arrays.asList(locations);
    }

    private List<String> setUpOccupations(){
        //There are only 2 Genders.
        String[] occupations = {"Student", "Professional", "Self-employed", "Others"};
        return Arrays.asList(occupations);
    }

    private String getTextViewNameFromId(int id) {
        if (id == R.id.gender) {
            return GENDER;
        } else if (id == R.id.city) {
            return CITY;
        } else if (id == R.id.neighbourhood) {
            return Constants.NEIGHBOURHOOD;
        } else if (id == R.id.occupation) {
            return Constants.OCCUPATION;
        }
        return null;
    }

    private void saveProfileToDB(){
        Map<String, Object> profileMap = new HashMap<>();
        profileMap.put(Constants.NAME, fullName);
        profileMap.put(Constants.DATE_OF_BIRTH, dateOfBirthTimeStamp);
        profileMap.put(GENDER, gender);
        profileMap.put(CITY, city);
        profileMap.put(NEIGHBOURHOOD, neighbourhood);
        profileMap.put(OCCUPATION, occupation);
        profileMap.put(BIO, bio);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(profileMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    //If everything went successfully, then we need
                    //to tell Firebase that this user has finished the
                    //Traits section
                    markProfileAsDone();
                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void markProfileAsDone(){
        Map<String, Object> traitsMap = new HashMap<>();
        traitsMap.put(Constants.SETUP_PROFILE_DONE, true);

        database.collection(Constants.ROOMMATES).document(currentUser.getUid())
                .set(traitsMap, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPref.getInstance(getApplicationContext()).saveBoolean(Constants.SETUP_PROFILE_DONE, true);
                    startActivity(new Intent(SetupProfileActivity.this, MainActivity.class));

                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(SetupProfileActivity.this, "Something happened. Try again.", Toast.LENGTH_SHORT).show();
                });
    }
}