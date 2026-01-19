package com.example.green_fleet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.PopupMenu;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment {

    public ReportFragment() {
        super(R.layout.fragment_report);
    }

    private final List<RadioButton> radioButtons = new ArrayList<>();
    private final List<TextView> filterLabels = new ArrayList<>();
    private MaterialButton btnDate;

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

        // get all ids with filter in id
        filterLabels.add(view.findViewById(R.id.tv_card_total_km_filter));
        filterLabels.add(view.findViewById(R.id.tv_card_total_vehicles_filter));
        filterLabels.add(view.findViewById(R.id.tv_card_total_gas_filter));
        filterLabels.add(view.findViewById(R.id.tv_card_total_kwh_filter));
        filterLabels.add(view.findViewById(R.id.tv_card_total_km_nv_filter));
        filterLabels.add(view.findViewById(R.id.tv_card_total_co2_emissions_filter));
        filterLabels.add(view.findViewById(R.id.tv_card_total_km_ev_filter));
        filterLabels.add(view.findViewById(R.id.tv_card_total_co2_savings_filter));
        btnDate = view.findViewById(R.id.mtbn_date);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(requireContext(), view);

        popup.getMenuInflater().inflate(R.menu.menu_date_selection, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_today) {
                    btnDate.setText("Today");

                    for (TextView tv : filterLabels) {
                        tv.setText("Today");
                    }
                    return true;

                } else if (id == R.id.menu_month) {
                    btnDate.setText("This month");

                    for (TextView tv : filterLabels) {
                        tv.setText("This month");
                    }
                    return true;
                }
                return false;
            }
        });

        popup.show();
    }
}