package com.theelitedevelopers.bunkies.modules.main.ad.room_ad;
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
import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityLocationRentAvailabilityBinding;
import com.theelitedevelopers.bunkies.modules.main.listing.models.RoommateListing;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class LocationRentAvailability extends AppCompatActivity {
    ActivityLocationRentAvailabilityBinding binding;
    Calendar availabilityDate = Calendar.getInstance();
    String date="", city="", streetAddress="", bio="";
    Timestamp dateAvailable;
    SimpleDateFormat simpleDateFormat, simpleTimeFormat;
    boolean availableImmediately = false, billsIncluded=false;
    int roomCount=1, rentPerYear=50000, deposit=10000;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationRentAvailabilityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextButton.setOnClickListener(v -> {
            if(validateUserInput()){
                RoommateListing roommateListing = new RoommateListing();
                roommateListing.setCity(city);
                roommateListing.setStreetAddress(streetAddress);
                roommateListing.setBio(bio);
                roommateListing.setRentPerYear(String.valueOf(rentPerYear));
                roommateListing.setDeposit(String.valueOf(deposit));
                roommateListing.setImmediately(availableImmediately);
                roommateListing.setDate(dateAvailable);

                Intent intent = new Intent(LocationRentAvailability.this, RoomOverViewAttributesActivity.class);
                intent.putExtra(Constants.ROOM_LISTING, roommateListing);
                startActivity(intent);
            }
        });

        binding.rentSeekBar.setMin(10000);
        binding.rentSeekBar.setMax(500000);
        //Set listener on Seekbar
        binding.rentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.rent.setText(NumberFormat.getNumberInstance(Locale.US).format(i));
                rentPerYear = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.depositSeekBar.setMin(10000);
        binding.depositSeekBar.setMax(500000);
        //Set listener on Seekbar
        binding.depositSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.deposit.setText(NumberFormat.getNumberInstance(Locale.US).format(i));
                deposit = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.availability.setOnClickListener(v -> {
            showDateTimePicker();
        });

        binding.availableImmediately.setOnCheckedChangeListener((compoundButton, b) -> {
            availableImmediately = b;
            if(b){
                binding.availability.setVisibility(View.GONE);
            }else {
                binding.availability.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean validateUserInput() {
        city = binding.city.getText().toString();
        streetAddress = binding.street.getText().toString();
        bio = binding.bio.getText().toString();
        if (city.isEmpty()) {
            displayToast( "Please enter the city where your room is located");
            return false;
        }
        if (streetAddress.isEmpty()) {
            displayToast( "Please enter the address where your room is located.");
            return false;
        }
        if (bio.isEmpty()) {
            displayToast( "Please enter your bio that will be visible to potential roommates.");
            return false;
        }
        if (!availableImmediately) {
            if(date.isEmpty()){
                displayToast("If this room is not available immediately, please select the date when it'll be available.");
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

    private void getTimeStamp(String date) throws ParseException {
        String destinationFormat = "EEE, d MMM yyyy HH:mm:ss";

        dateAvailable = new Timestamp(Objects.requireNonNull(
                AppUtils.Companion.convertToDateFormat(destinationFormat, date)));
    }

    private void displayToast(String message){
        Toast.makeText(LocationRentAvailability.this, message, Toast.LENGTH_SHORT).show();
    }
}