package com.example.moneymanager.ui.Fragment.service;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterRCDebets;
import com.example.moneymanager.adapter.AdapterRCRevenue;
import com.example.moneymanager.model.Revenue;

import java.util.ArrayList;
import java.util.List;

public class RevenueFragment extends Fragment {

    private RecyclerView rc_revenue;
    private List<Revenue> revenues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_revenue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_revenue = view.findViewById(R.id.rc_revenue);

        revenues = new ArrayList<>();
        revenues.add(new Revenue(R.drawable.rewards, getContext().getString(R.string.txt_get_rewards)));
        revenues.add(new Revenue(R.drawable.interest_rate, getContext().getString(R.string.txt_interest_rate)));
        revenues.add(new Revenue(R.drawable.salary, getContext().getString(R.string.txt_salary)));
        revenues.add(new Revenue(R.drawable.money, getContext().getString(R.string.txt_money)));
        revenues.add(new Revenue(R.drawable.sale, getContext().getString(R.string.txt_sale)));

        rc_revenue.hasFixedSize();
        rc_revenue.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rc_revenue.setAdapter(new AdapterRCRevenue(getActivity(),revenues));
    }
}
