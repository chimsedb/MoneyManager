package com.example.moneymanager.ui.Fragment.service;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterRCDebets;
import com.example.moneymanager.model.Debet;

import java.util.ArrayList;
import java.util.List;

public class DebetsFragment extends Fragment {

    private RecyclerView rc_debets;
    private List<Debet> debets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        debets = new ArrayList<>();
        debets.add(new Debet(R.drawable.get_debets, getContext().getString(R.string.txt_get_debets)));
        debets.add(new Debet(R.drawable.get_money, getContext().getString(R.string.txt_get_money)));
        debets.add(new Debet(R.drawable.give_money, getContext().getString(R.string.txt_give_money)));
        debets.add(new Debet(R.drawable.give_debets, getContext().getString(R.string.txt_give_debets)));

        rc_debets = view.findViewById(R.id.rc_debets);
        rc_debets.hasFixedSize();
        rc_debets.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rc_debets.setAdapter(new AdapterRCDebets(getActivity(),debets));
    }
}
