package com.theelitedevelopers.bunkies.modules.main.ad.roommate_ad;
import static com.theelitedevelopers.bunkies.core.utils.Constants.CITY;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityPostRoommateBinding;
import com.theelitedevelopers.bunkies.modules.main.MainActivity;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class PostRoommateActivity extends AppCompatActivity {
    ActivityPostRoommateBinding binding;
    Calendar availabilityDate = Calendar.getInstance();
    String date="", city="";
    Timestamp dateAvailable;
    SimpleDateFormat simpleDateFormat, simpleTimeFormat;
    boolean availableImmediately = false, billsIncluded=false;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    int roomCount=1, budget=20000;
    Roommate roommate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostRoommateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database.collection(Constants.ROOMMATES)
                .whereEqualTo("uid", SharedPref.getInstance(getApplicationContext()).getString(Constants.UID))
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    if(!value.getDocuments().isEmpty()) {
                        roommate = value.getDocuments().get(0).toObject(Roommate.class);
                    }
                });


        binding.addRoom.setOnClickListener(v -> {
            roomCount+=1;
            binding.roomCount.setText(String.valueOf(roomCount));
        });

        binding.removeRoom.setOnClickListener(v -> {
            if(roomCount > 1){
                roomCount-=1;
                binding.roomCount.setText(String.valueOf(roomCount));
            }else {
                Toast.makeText(PostRoommateActivity.this, "Number of rooms has to be at least 1.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.availability.setOnClickListener(v -> {
            showDateTimePicker();
        });

        binding.budgetSeekBar.setMin(10000);
        binding.budgetSeekBar.setMax(500000);
        //Set listener on Seekbar
        binding.budgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.budget.setText(NumberFormat.getNumberInstance(Locale.US).format(i));
                budget = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.availableImmediately.setOnCheckedChangeListener((compoundButton, b) -> {
            availableImmediately = b;
            if(b){
                binding.availability.setVisibility(View.GONE);
            }else {
                binding.availability.setVisibility(View.VISIBLE);
            }
        });

        binding.billsIncluded.setOnCheckedChangeListener((compoundButton, b) -> {
            billsIncluded = b;
        });

        binding.postButton.setOnClickListener(v -> {
            if(validateUserInput()){
                binding.progressBar.setVisibility(View.VISIBLE);
                saveRoommateToListing();
            }
        });
    }

    private boolean validateUserInput() {
        city = binding.neighbourhood.getText().toString();
        if (city.isEmpty()) {
            displayToast( "Please enter the city you wish to move to.");
            return false;
        }
        if (!availableImmediately) {
            if(date.isEmpty()){
                displayToast("If you are not, available immediately, please select the date when you'll be available.");
                return false;
            }
        }
        return true;
    }

    public void showDateTimePicker(){
        final Calendar currentDate = Calendar.getInstance();
        availabilityDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            availabilityDate.set(year, month, dayOfMonth);

            simpleDateFormat = new SimpleDateFormat("EEE dd MMM yyyy");
            simpleTimeFormat = new SimpleDateFormat("hh:mm aa");

            String sourceFormat = "EEE MMM d HH:mm:ss z yyyy";
            String destinationFormat = "EEE, d MMM yyyy HH:mm:ss";

            //convert date to Universal format
            date = AppUtils.Companion.convertDateFromOneFormatToAnother(sourceFormat, destinationFormat, availabilityDate.getTime().toString());
            binding.availability.setText(simpleDateFormat.format(availabilityDate.getTime()));

            try {
                getTimeStamp(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }

    private void saveRoommateToListing(){
        Map<String, Object> roommateMap = new HashMap<>();
        roommateMap.put(CITY, city);
        roommateMap.put(Constants.DATE_OF_BIRTH, roommate.getDateOfBirth());
        roommateMap.put(Constants.GENDER, roommate.getGender());
        roommateMap.put(Constants.OCCUPATION, roommate.getOccupation());
        if(availableImmediately){
            roommateMap.put("immediately", true);
            roommateMap.put("date", "");
        }else {
            roommateMap.put("immediately", false);
            roommateMap.put("date", dateAvailable);
        }
        roommateMap.put("adType", "roommate");
        roommateMap.put("budget", budget);
        roommateMap.put("numberOfRooms", String.valueOf(roomCount));
        roommateMap.put("billsIncluded", billsIncluded);
        roommateMap.put(Constants.UID, currentUser.getUid());

        database.collection(Constants.LISTINGS)
                .add(roommateMap)
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    displayToast("Roommate ad added successfully. Go to My Listing in Profile to see your ads");
                    startActivity(new Intent(PostRoommateActivity.this, MainActivity.class));
                    finishAffinity();
                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void getTimeStamp(String date) throws ParseException {
        String destinationFormat = "EEE, d MMM yyyy HH:mm:ss";

        dateAvailable = new Timestamp(Objects.requireNonNull(
                AppUtils.Companion.convertToDateFormat(destinationFormat, date)));
    }

    private void displayToast(String message){
        Toast.makeText(PostRoommateActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}