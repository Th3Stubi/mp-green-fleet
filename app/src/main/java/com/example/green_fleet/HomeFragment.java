package com.example.green_fleet;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
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
        View btnChooseVehicle = view.findViewById(R.id.btn_chose_vehicle);

        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        trackingRunning = prefs.getBoolean(KEY_TRACKING, false);

        updateTrackingButton(btnTracking, btnChooseVehicle, trackingRunning);

        btnTracking.setOnClickListener(v -> {
            trackingRunning = !trackingRunning;
            prefs.edit().putBoolean(KEY_TRACKING, trackingRunning).apply();
            updateTrackingButton(btnTracking, btnChooseVehicle, trackingRunning);
        });
    }

    private void updateTrackingButton(MaterialButton button, View chooseVehicleBtn, boolean running) {
        if (running) {
            button.setText(R.string.tracking_stoppen);
            button.setIconResource(R.drawable.ic_pause);
            chooseVehicleBtn.setVisibility(View.INVISIBLE); // Invisible statt Gone weil sonst view keinen platz mehr einnimmt
        } else {
            button.setText(R.string.tracking_starten);
            button.setIconResource(R.drawable.ic_play);
            chooseVehicleBtn.setVisibility(View.VISIBLE);
        }
    }
}
