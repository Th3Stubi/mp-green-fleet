package com.example.green_fleet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
        super(R.layout.fragment_history);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ViewGroup list = view.findViewById(R.id.history_list);
        if (list == null) return;
        for (int i = 0; i < list.getChildCount(); i++) {
            View item = list.getChildAt(i);

            ImageView arrow = item.findViewById(R.id.iv_expand);
            View details = item.findViewById(R.id.ll_details);

            if (arrow == null || details == null) continue;

            arrow.setOnClickListener(v -> {
                boolean open = details.getVisibility() == View.VISIBLE;
                details.setVisibility(open ? View.GONE : View.VISIBLE);
                arrow.setImageResource(open ? R.drawable.ic_expand_down : R.drawable.ic_expand_up);
            });
        }
    }}