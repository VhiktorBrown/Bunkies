package com.theelitedevelopers.bunkies.modules.main.roommate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theelitedevelopers.bunkies.R;
import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityRoommateDetailsBinding;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.models.Interest;
import com.theelitedevelopers.bunkies.modules.account_setup.personal.models.Trait;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;
import com.theelitedevelopers.bunkies.modules.main.inbox.ChatActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RoommateDetailsActivity extends AppCompatActivity {
    ActivityRoommateDetailsBinding binding;
    ArrayList<Trait> traits = new ArrayList<>();
    ArrayList<Interest> interests = new ArrayList<>();
    Roommate adRoommate, roommate;
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoommateDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adRoommate = getIntent().getParcelableExtra(Constants.ROOMMATE_DETAILS);

        database.collection(Constants.ROOMMATES)
                .whereEqualTo("uid", adRoommate.getUid())
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    if(!value.getDocuments().isEmpty()) {
                        roommate = value.getDocuments().get(0).toObject(Roommate.class);
                        setRoommateDetails(roommate);
                    }
                });

        traits.add(new Trait("Hardworking"));
        traits.add(new Trait("Peaceful"));
        traits.add(new Trait("Relaxed"));
        traits.add(new Trait("Clean"));
        traits.add(new Trait("Diligent"));
        traits.add(new Trait("Organised"));
        traits.add(new Trait("Communicative"));

        interests.add(new Interest("Music"));
        interests.add(new Interest("Photography"));
        interests.add(new Interest("Swimming"));
        interests.add(new Interest("Fashion"));
        interests.add(new Interest("Blogging"));
        interests.add(new Interest("Shopping"));
        interests.add(new Interest("Writing"));

        addChips();

        binding.sendAMessageButton.setOnClickListener(v -> {
            startActivity(new Intent(RoommateDetailsActivity.this, ChatActivity.class)
                    .putExtra(Constants.RECEIVER_UID, adRoommate.getUid())
                    .putExtra(Constants.NAME, roommate.getName()));
        });

        setRoommateDetails(roommate);
    }

    private void addChips(){
        for(Trait trait : traits){
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.custom_static_chip_layout, binding.personalityChipGroup, false);
            chip.setText(trait.getTraitName());
            binding.personalityChipGroup.addView(chip);
        }

        for(Interest interest : interests){
            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.custom_static_chip_layout, binding.interestsChipGroup, false);
            chip.setText(interest.getName());
            binding.interestsChipGroup.addView(chip);
        }
    }

    private void setRoommateDetails(Roommate roommate){
        binding.location.setText(adRoommate.getCity());
        binding.budget.setText("NGN"+ NumberFormat.getNumberInstance(Locale.US).format(adRoommate.getBudget())+"/per year");
        binding.rooms.setText(adRoommate.getNumberOfRooms()+" shared room");
        if(adRoommate.isBillsIncluded()){
            binding.bills.setText("Included");
        }else {
            binding.bills.setText("Not included");
        }
        if(adRoommate.isImmediately()){
            binding.availability.setText("Immediately");
        }else {
            binding.availability.setText(AppUtils.Companion.convertDateToPresentableFormatWithOnlyDate(
                    AppUtils.Companion.fromTimeStampToString(
                            adRoommate.getDate().getSeconds()
                    )));
        }
        if(roommate != null){
            binding.roommateNameAge.setText(roommate.getName()+", 21");
            binding.bio.setText(roommate.getBio());
            binding.gender.setText(roommate.getGender());
            binding.work.setText(roommate.getOccupation());
        }
    }

}