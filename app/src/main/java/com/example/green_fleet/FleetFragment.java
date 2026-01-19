package com.example.green_fleet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FleetFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_MODE = "mode";
    public static final int MODE_VEHICLES = 1;
    public static final int MODE_DRIVERS = 2;
    private int mode = MODE_VEHICLES;

    private String mParam1;
    private String mParam2;

    public FleetFragment() { }

    public static FleetFragment newInstance(String param1, String param2, int mode) {
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
        int layout = (mode == MODE_DRIVERS) ? R.layout.fragment_fleet2 : R.layout.fragment_fleet;
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View drivers = view.findViewById(R.id.btn_nav_drivers);
        if (drivers != null) {
            drivers.setOnClickListener(v ->
                    NavHostFragment.findNavController(FleetFragment.this)
                            .navigate(R.id.fleetFragment2)
            );
        }

        SearchView sv = view.findViewById(R.id.sv_fleet);
        ViewGroup list = view.findViewById(R.id.list_container);

        if (sv != null && list != null) {
            // am Anfang alles ausblenden
           filterList(list, "");

            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override public boolean onQueryTextSubmit(String query) { return false; }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterList(list, newText);
                    return true;
                }
            });
        }
    }

    private void filterList(ViewGroup list, String query) {
        String q = (query == null) ? "" : query.trim().toLowerCase();

        for (int i = 0; i < list.getChildCount(); i++) {
            View item = list.getChildAt(i);

            // Vehicles
            TextView plate = item.findViewById(R.id.tv_license_plate);
            TextView model = item.findViewById(R.id.tv_vehicle_type);

            // Drivers: (bei TextView mit dieser ID)
            // Später eventuell auch Job/Position miteinbinden
            TextView driverName = item.findViewById(R.id.tv_driver_name);

            String text = "";
            if (driverName != null) {
                text = driverName.getText().toString();
            } else {
                String p = (plate != null) ? plate.getText().toString() : "";
                String m = (model != null) ? model.getText().toString() : "";
                text = (p + " " + m).trim();
            }

            // nur anzeigen, wenn gesucht wird
            // boolean show = !q.isEmpty() && text.toLowerCase().contains(q);
            boolean show = q.isEmpty() || text.toLowerCase().contains(q);
            item.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
