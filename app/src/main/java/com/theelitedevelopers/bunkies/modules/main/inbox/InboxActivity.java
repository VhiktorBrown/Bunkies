package com.theelitedevelopers.bunkies.modules.main.inbox;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.theelitedevelopers.bunkies.databinding.ActivityInboxBinding;

public class InboxActivity extends AppCompatActivity {
    ActivityInboxBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}