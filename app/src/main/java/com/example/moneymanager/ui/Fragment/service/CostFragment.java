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
import com.example.moneymanager.adapter.AdapterRCCost;
import com.example.moneymanager.model.Cost;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CostFragment extends Fragment {

    private RecyclerView rc_costs;
    private List<Cost> costs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cost, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_costs = view.findViewById(R.id.rc_costs);
        costs = new ArrayList<>();
        addItemCost();

        rc_costs.hasFixedSize();
        rc_costs.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rc_costs.setAdapter(new AdapterRCCost(costs,getActivity()));
    }

    private void addItemCost() {
        costs.add(new Cost(R.drawable.food, getContext().getString(R.string.txt_food)));
        costs.add(new Cost(R.drawable.bill, getContext().getString(R.string.txt_bill)));
        costs.add(new Cost(R.drawable.car, getContext().getString(R.string.txt_move)));
        costs.add(new Cost(R.drawable.shopping, getContext().getString(R.string.txt_shopping)));
        costs.add(new Cost(R.drawable.love, getContext().getString(R.string.txt_love)));
        costs.add(new Cost(R.drawable.game, getContext().getString(R.string.txt_game)));
        costs.add(new Cost(R.drawable.travel, getContext().getString(R.string.txt_travel)));
        costs.add(new Cost(R.drawable.health, getContext().getString(R.string.txt_health)));
        costs.add(new Cost(R.drawable.gift, getContext().getString(R.string.txt_gift)));
        costs.add(new Cost(R.drawable.house, getContext().getString(R.string.txt_family)));
        costs.add(new Cost(R.drawable.education, getContext().getString(R.string.txt_education)));
        costs.add(new Cost(R.drawable.investment, getContext().getString(R.string.txt_investment)));
        costs.add(new Cost(R.drawable.withdrawal, getContext().getString(R.string.txt_withdrawal)));
    }
}
