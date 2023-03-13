package com.theelitedevelopers.bunkies.modules.main.inbox;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.ActivityStartNewChatBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;
import com.theelitedevelopers.bunkies.modules.main.inbox.adapters.StartNewChatAdapter;

import java.util.ArrayList;

public class StartNewChatActivity extends AppCompatActivity {
    ActivityStartNewChatBinding binding;
    StartNewChatAdapter adapter;
    ArrayList<Roommate> roommates = new ArrayList<>();
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartNewChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.newChatRecyclerView.setLayoutManager(layoutManager);
        binding.newChatRecyclerView.setHasFixedSize(true);

        adapter = new StartNewChatAdapter(this, roommates);
        binding.newChatRecyclerView.setAdapter(adapter);

        binding.goBack.setOnClickListener(v -> onBackPressed());

        database.collection(Constants.ROOMMATES)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if(!task.getResult().isEmpty()){
                            roommates.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(!document.toObject(Roommate.class).getUid().equals(SharedPref.getInstance(getApplicationContext()).getString(Constants.UID))){
                                    roommates.add(document.toObject(Roommate.class));
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                            }
                            adapter.setList(roommates);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}