package com.example.green_fleet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment {

    public ReportFragment() {
        super(R.layout.fragment_report);
    }

    private final List<RadioButton> radioButtons = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioButtons.add(view.findViewById(R.id.rbtn_all));
        radioButtons.add(view.findViewById(R.id.rbtn_suv));
        radioButtons.add(view.findViewById(R.id.rbtn_cars));
        radioButtons.add(view.findViewById(R.id.rbtn_electro));
        radioButtons.add(view.findViewById(R.id.rbtn_truck));
        radioButtons.add(view.findViewById(R.id.rbtn_motorcycle));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                RadioButton clickedBtn = (RadioButton) clickedView;

                for (RadioButton rb : radioButtons) {
                    if (rb.getId() != clickedBtn.getId()) {
                        rb.setChecked(false);
                    }
                }
            }
        };

        for (RadioButton rb : radioButtons) {
            rb.setOnClickListener(listener);
        }
    }
}