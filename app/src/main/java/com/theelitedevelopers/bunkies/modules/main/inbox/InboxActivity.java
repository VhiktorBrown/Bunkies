package com.theelitedevelopers.bunkies.modules.main.inbox;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theelitedevelopers.bunkies.core.utils.AppUtils;
import com.theelitedevelopers.bunkies.databinding.ActivityInboxBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.Inbox;
import com.theelitedevelopers.bunkies.modules.main.inbox.adapters.InboxAdapter;

import java.util.ArrayList;
import java.util.Date;

public class InboxActivity extends AppCompatActivity {
    ActivityInboxBinding binding;
    InboxAdapter adapter;
    ArrayList<Inbox> inboxArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInboxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.inboxRecyclerView.setLayoutManager(layoutManager);
        binding.inboxRecyclerView.setHasFixedSize(true);

        adapter = new InboxAdapter(this, inboxArrayList);
        binding.inboxRecyclerView.setAdapter(adapter);

        binding.send.setOnClickListener(v -> {
            if(binding.sendAMessageEditText.getText().length() > 0){
                populateDummyData(binding.sendAMessageEditText.getText().toString());
            }
            binding.sendAMessageEditText.getText().clear();
        });

    }

    public void populateDummyData(String message){
        String date  = AppUtils.Companion.convertDateFromOneFormatToAnother("EEE MMM d HH:mm:ss z yyyy", "EEE, d MMM yyyy HH:mm:ss", new Date().toString());
        assert date != null;
        inboxArrayList.add(new Inbox("1", message, AppUtils.Companion.getInboxDate(date)));
        inboxArrayList.add(new Inbox("2", message, AppUtils.Companion.getInboxDate(date)));

        adapter.setList(inboxArrayList);
    }
}