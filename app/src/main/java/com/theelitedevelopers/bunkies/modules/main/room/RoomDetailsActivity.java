package com.theelitedevelopers.bunkies.modules.main.room;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityRoomDetailsBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.RoomDetails;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;
import com.theelitedevelopers.bunkies.modules.main.inbox.ChatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class RoomDetailsActivity extends AppCompatActivity {
    ActivityRoomDetailsBinding binding;
    RoomDetails roomDetails;
    Roommate roommate;
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        roomDetails = getIntent().getParcelableExtra(Constants.ROOM_DETAILS);

        setRoomDetails(roommate);

        database.collection(Constants.ROOMMATES)
                .whereEqualTo("uid", roomDetails.getUid())
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    if(!value.getDocuments().isEmpty()) {
                        roommate = value.getDocuments().get(0).toObject(Roommate.class);
                        setRoomDetails(roommate);
                    }
                });

        binding.sendMessageButton.setOnClickListener(v -> {
            startActivity(new Intent(RoomDetailsActivity.this, ChatActivity.class)
                    .putExtra(Constants.RECEIVER_UID, roomDetails.getUid())
                    .putExtra(Constants.NAME, roommate.getName()));
        });
    }

    private void setRoomDetails(Roommate roommate){
        binding.roomType.setText(roomDetails.getRoomType());
        binding.bio.setText(roomDetails.getBio());
        binding.rent.setText("NGN "+NumberFormat.getNumberInstance(Locale.US).format(roomDetails.getRentPerYear())+"/year");
        binding.room.setText(roomDetails.getNumberOfRooms()+" "+roomDetails.getRoomType());
        binding.deposit.setText("NGN"+NumberFormat.getNumberInstance(Locale.US).format(roomDetails.getBudget()));
        if(roomDetails.isImmediately()){
            binding.availability.setText("Immediately");
        }else {
            binding.availability.setText(AppUtils.Companion.convertDateToPresentableFormatWithOnlyDate(
                    AppUtils.Companion.fromTimeStampToString(
                            roomDetails.getDate().getSeconds()
                    )));
        }

        //set User Details
        if(roommate != null){
            binding.roommateName.setText(roommate.getName());
            binding.roommateOccupation.setText(roommate.getOccupation()+" at "+roommate.getCity());

        }

    }

}