package com.theelitedevelopers.bunkies.modules.main.ad.room_ad;

import static com.theelitedevelopers.bunkies.core.utils.Constants.CITY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityRoomOverViewAttributesBinding;
import com.theelitedevelopers.bunkies.modules.main.MainActivity;
import com.theelitedevelopers.bunkies.modules.main.listing.models.RoommateListing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomOverViewAttributesActivity extends AppCompatActivity {
    ActivityRoomOverViewAttributesBinding binding;
    int roomCount=1, count;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    RoommateListing roommateListing;
    List<String> suitableFor=new ArrayList<>(), roomAttributes=new ArrayList<>(), roomType=new ArrayList<>(), selectedRoomType=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomOverViewAttributesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        roommateListing = getIntent().getParcelableExtra(Constants.ROOM_LISTING);
        roomType.add("Shared bedroom");
        roomType.add("1 bedroom");

        addChips();

        binding.postButton.setOnClickListener(v -> {
            if(getSelectedChips()){
                binding.progressBar.setVisibility(View.VISIBLE);
                saveRoomToListing();
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
               displayToast("Number of rooms has to be at least 1.");
            }
        });

        binding.studentCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                suitableFor.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < suitableFor.size(); i++){
                    if(suitableFor.get(i).equals(text)){
                        suitableFor.remove(i);
                    }
                }
            }
        });
        binding.veganCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                suitableFor.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < suitableFor.size(); i++){
                    if(suitableFor.get(i).equals(text)){
                        suitableFor.remove(i);
                    }
                }
            }
        });
        binding.smokingCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                suitableFor.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < suitableFor.size(); i++){
                    if(suitableFor.get(i).equals(text)){
                        suitableFor.remove(i);
                    }
                }
            }
        });
        binding.petCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                suitableFor.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < suitableFor.size(); i++){
                    if(suitableFor.get(i).equals(text)){
                        suitableFor.remove(i);
                    }
                }
            }
        });

        binding.acCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                roomAttributes.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < roomAttributes.size(); i++){
                    if(roomAttributes.get(i).equals(text)){
                        roomAttributes.remove(i);
                    }
                }
            }
        });
        binding.wifiCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                roomAttributes.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < roomAttributes.size(); i++){
                    if(roomAttributes.get(i).equals(text)){
                        roomAttributes.remove(i);
                    }
                }
            }
        });
        binding.balconyCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                roomAttributes.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < roomAttributes.size(); i++){
                    if(roomAttributes.get(i).equals(text)){
                        roomAttributes.remove(i);
                    }
                }
            }
        });

        binding.tvCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                roomAttributes.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < roomAttributes.size(); i++){
                    if(roomAttributes.get(i).equals(text)){
                        roomAttributes.remove(i);
                    }
                }
            }
        });
        binding.furnitureCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                roomAttributes.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < roomAttributes.size(); i++){
                    if(roomAttributes.get(i).equals(text)){
                        roomAttributes.remove(i);
                    }
                }
            }
        });
        binding.renovatedCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                roomAttributes.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < roomAttributes.size(); i++){
                    if(roomAttributes.get(i).equals(text)){
                        roomAttributes.remove(i);
                    }
                }
            }
        });
        binding.washingMachineCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                roomAttributes.add(compoundButton.getText().toString());
            }else {
                String text = compoundButton.getText().toString();
                for (int i = 0; i < roomAttributes.size(); i++){
                    if(roomAttributes.get(i).equals(text)){
                        roomAttributes.remove(i);
                    }
                }
            }
        });

    }

    @SuppressLint("ResourceType")
    private void addChips(){
        for(String roomType : roomType){
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.custom_chip_layout, binding.rentTypeChipGroup, false);
            chip.setText(roomType);
            binding.rentTypeChipGroup.addView(chip);
        }
    }

    private boolean getSelectedChips(){
        count = binding.rentTypeChipGroup.getChildCount();
        if(count > 0){
            int trackCount = 0;
            while(trackCount < count){
                Chip chip = (Chip) binding.rentTypeChipGroup.getChildAt(trackCount);
                if(chip.isChecked()){
                    selectedRoomType.add(chip.getText().toString());
                }
                trackCount++;
            }
            return true;
        }else {
            Toast.makeText(RoomOverViewAttributesActivity.this, "No room type was selected", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void saveRoomToListing(){
        Map<String, Object> roomMap = new HashMap<>();
        roomMap.put(CITY, roommateListing.getCity());
        roomMap.put("streetAddress", roommateListing.getStreetAddress());
        if(roommateListing.isImmediately()){
            roomMap.put("immediately", true);
            roomMap.put("date", "");
        }else {
            roomMap.put("immediately", false);
            roomMap.put("date", roommateListing.getDate());
        }
        roomMap.put("adType", "room");
        roomMap.put("rent", roommateListing.getRentPerYear());
        roomMap.put("deposit", roommateListing.getDeposit());
        roomMap.put("bio", roommateListing.getBio());
        roomMap.put("numberOfRooms", String.valueOf(roomCount));
        roomMap.put("suitableFor", suitableFor);
        roomMap.put("roomAttributes", roomAttributes);
        roomMap.put("roomType", selectedRoomType.get(0));
        roomMap.put(Constants.UID, currentUser.getUid());

        database.collection(Constants.LISTINGS)
                .add(roomMap)
                .addOnSuccessListener(unused -> {
                    binding.progressBar.setVisibility(View.GONE);
                    displayToast("Room ad added successfully. Go to My Listing in Profile to see your ads");
                    startActivity(new Intent(RoomOverViewAttributesActivity.this, MainActivity.class));
                    finishAffinity();
                })
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                });
    }

    private void displayToast(String message){
        Toast.makeText(RoomOverViewAttributesActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}