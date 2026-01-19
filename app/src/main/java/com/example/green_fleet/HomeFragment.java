package com.example.green_fleet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String PREFS = "greenfleet_prefs";
    private static final String KEY_TRACKING = "tracking_running";

    private boolean trackingRunning = false;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnTracking = view.findViewById(R.id.btn_tracking);

        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        trackingRunning = prefs.getBoolean(KEY_TRACKING, false);

        updateTrackingButton(btnTracking, trackingRunning);

        btnTracking.setOnClickListener(v -> {
            trackingRunning = !trackingRunning;
            prefs.edit().putBoolean(KEY_TRACKING, trackingRunning).apply();
            updateTrackingButton(btnTracking, trackingRunning);
        });
    }

    private void updateTrackingButton(MaterialButton button, boolean running) {
        if (running) {
            button.setText(R.string.tracking_stoppen);
            button.setIconResource(R.drawable.ic_pause);
        } else {
            button.setText(R.string.tracking_starten);
            button.setIconResource(R.drawable.ic_play);
        }
    }
}