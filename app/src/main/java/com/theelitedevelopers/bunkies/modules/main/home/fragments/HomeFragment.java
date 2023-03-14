package com.theelitedevelopers.bunkies.modules.main.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.theelitedevelopers.bunkies.core.data.local.SharedPref;
import com.theelitedevelopers.bunkies.core.utils.Constants;
import com.theelitedevelopers.bunkies.databinding.FragmentHomeBinding;
import com.theelitedevelopers.bunkies.modules.main.data.models.RoomDetails;
import com.theelitedevelopers.bunkies.modules.main.data.models.Roommate;
import com.theelitedevelopers.bunkies.modules.main.home.adapters.AvailableRoommatesAdapter;
import com.theelitedevelopers.bunkies.modules.main.home.adapters.CampusAdapter;
import com.theelitedevelopers.bunkies.modules.main.home.adapters.FeaturedRoomAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    AvailableRoommatesAdapter availableRoommatesAdapter;
    FeaturedRoomAdapter featuredRoomAdapter;
    CampusAdapter campusAdapter;
    ArrayList<RoomDetails> rooms = new ArrayList<>();
    ArrayList<Roommate> roommates = new ArrayList<>();
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    List<String> cities = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.homeMessage.setText("Hi "+ SharedPref.getInstance(requireActivity()).getString(Constants.NAME)
        +"! Let's help you find your roommate.");

        cities.add("Awka");
        cities.add("Enugu");
        cities.add("Owerri");
        cities.add("Port");
        cities.add("Lagos");
        cities.add("Abuja");


        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        binding.exploreCampusesRecyclerView.setLayoutManager(layoutManager);
        binding.roomsRecyclerView.setLayoutManager(layoutManager1);
        binding.availableRoommatesRecyclerView.setLayoutManager(layoutManager2);

        binding.exploreCampusesRecyclerView.setHasFixedSize(true);
        binding.roomsRecyclerView.setHasFixedSize(true);
        binding.availableRoommatesRecyclerView.setHasFixedSize(true);

        //setUpDummyLists();

        fetchData();

        campusAdapter = new CampusAdapter(requireContext(), cities);
        featuredRoomAdapter = new FeaturedRoomAdapter(requireContext(), rooms);
        availableRoommatesAdapter = new AvailableRoommatesAdapter(requireContext(), roommates);

        binding.exploreCampusesRecyclerView.setAdapter(campusAdapter);
        binding.roomsRecyclerView.setAdapter(featuredRoomAdapter);
        binding.availableRoommatesRecyclerView.setAdapter(availableRoommatesAdapter);

        return binding.getRoot();
    }

    private void fetchData(){
        database.collection(Constants.LISTINGS)
                .whereEqualTo("adType", "room")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    if(!value.getDocuments().isEmpty()) {
                        rooms.clear();
                        for (DocumentSnapshot documentSnapshot : value.getDocuments()) {
                            RoomDetails roomDetails = documentSnapshot.toObject(RoomDetails.class);
                            rooms.add(roomDetails);
                        }
                        featuredRoomAdapter.setList(rooms);
                    }
                });

        database.collection(Constants.LISTINGS)
                .whereEqualTo("adType", "roommate")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    if(!value.getDocuments().isEmpty()) {
                        roommates.clear();
                        for (DocumentSnapshot documentSnapshot : value.getDocuments()) {
                            Roommate roommate = documentSnapshot.toObject(Roommate.class);
                            roommates.add(roommate);
                        }
                        availableRoommatesAdapter.setList(roommates);
                    }
                });
    }


    private void setUpDummyLists(){
        rooms.add(new RoomDetails(null));
        rooms.add(new RoomDetails(null));
        rooms.add(new RoomDetails(null));
        rooms.add(new RoomDetails(null));
        rooms.add(new RoomDetails(null));
        rooms.add(new RoomDetails(null));
        rooms.add(new RoomDetails(null));


        roommates.add(new Roommate(null));
        roommates.add(new Roommate(null));
        roommates.add(new Roommate(null));
        roommates.add(new Roommate(null));
        roommates.add(new Roommate(null));
        roommates.add(new Roommate(null));
        roommates.add(new Roommate(null));
        roommates.add(new Roommate(null));
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    public void onStart() {
        fetchData();
        super.onStart();
    }
}