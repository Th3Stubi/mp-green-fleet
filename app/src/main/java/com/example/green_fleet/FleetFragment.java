package com.example.green_fleet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FleetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FleetFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Switch beetween Vehicles N Drivers
    private static final String ARG_MODE = "mode";
    public static final int MODE_VEHICLES = 1;
    public static final int MODE_DRIVERS = 2;
    private int mode = MODE_VEHICLES;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FleetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FleetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FleetFragment newInstance(String param1, String param2,int mode) {
        FleetFragment fragment = new FleetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_MODE, mode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mode = getArguments().getInt(ARG_MODE, MODE_VEHICLES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int layout = (mode == MODE_DRIVERS) ? R.layout.fragment_fleet2 : R.layout.fragment_fleet;
        // return inflater.inflate(R.layout.fragment_fleet, container, false);
        return inflater.inflate(layout, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        View drivers = view.findViewById(R.id.btn_nav_drivers);
        drivers.setOnClickListener(v ->
                NavHostFragment.findNavController(FleetFragment.this)
                        .navigate(R.id.fleetFragment2)
        );
    }




}