package com.example.green_fleet;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FleetFragment2 extends Fragment {
    public FleetFragment2() {
        super(R.layout.fragment_fleet2);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        View vehicles = view.findViewById(R.id.btn_nav_vehicles);
        vehicles.setOnClickListener(v ->
                NavHostFragment.findNavController(FleetFragment2.this)
                        .navigate(R.id.fleetFragment)
        );
    }
}

