package com.theelitedevelopers.bunkies.modules.main.inbox.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.theelitedevelopers.bunkies.databinding.FragmentInboxBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.Inbox;
import com.theelitedevelopers.bunkies.modules.main.inbox.adapters.InboxListAdapter;

import java.util.ArrayList;

public class InboxFragment extends Fragment {
    FragmentInboxBinding binding;
    InboxListAdapter adapter;
    ArrayList<Inbox> inboxArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInboxBinding.inflate(inflater, container,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        binding.inboxRecyclerView.setLayoutManager(linearLayoutManager);

        binding.inboxRecyclerView.setHasFixedSize(true);
        adapter = new InboxListAdapter(requireContext(), setUpDummyArrayList());
        binding.inboxRecyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    private ArrayList<Inbox> setUpDummyArrayList(){
        inboxArrayList.add(new Inbox("Regina Johnsons", "Does our meeting still hold for tomorrow?"));
        inboxArrayList.add(new Inbox("Chinonso Chukwudi", "I found you here and I think we're a match. Where do you stay?"));
        inboxArrayList.add(new Inbox("Sophia Jennings", "I found you here and I think we're a match. Where do you stay?"));

        return inboxArrayList;
    }
}