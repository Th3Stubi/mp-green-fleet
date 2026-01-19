package com.example.green_fleet;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FleetFragment2 extends Fragment {
    public FleetFragment2() {
        super(R.layout.fragment_fleet2);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View vehicles = view.findViewById(R.id.btn_nav_vehicles);
        if (vehicles != null) {
            vehicles.setOnClickListener(v -> {
                // zurück zu Vehicles
                NavHostFragment.findNavController(FleetFragment2.this).popBackStack();
            });
        }

        SearchView sv = view.findViewById(R.id.sv_fleet);
        ViewGroup list = view.findViewById(R.id.list_container);

        if (sv != null && list != null) {

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

            TextView driverName = item.findViewById(R.id.tv_driver_name);
           // optional job TextView job = item.findViewById(R.id.);

            String text = "";
            if (driverName != null) text += driverName.getText().toString();
           // if (job != null) text += " " + job.getText().toString();

            // Variante: erst anzeigen wenn gesucht wird
            boolean show = !q.isEmpty() && text.toLowerCase().contains(q);

            // Variante: immer anzeigen wenn Suche leer ist (statt oben)
            // boolean show = q.isEmpty() || text.toLowerCase().contains(q);

            item.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
