package com.theelitedevelopers.bunkies.modules.main.room;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.theelitedevelopers.bunkies.databinding.ActivityRoomDetailsBinding;

public class RoomDetailsActivity extends AppCompatActivity {
    ActivityRoomDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}