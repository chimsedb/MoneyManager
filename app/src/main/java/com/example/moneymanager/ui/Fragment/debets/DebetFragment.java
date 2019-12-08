package com.example.moneymanager.ui.Fragment.debets;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterViewPagerDebets;
import com.example.moneymanager.adapter.AdapterViewPagerTransaction;
import com.google.android.material.tabs.TabLayout;

public class DebetFragment extends Fragment {

    private ViewPager view_pager_debets;
    private AdapterViewPagerDebets adapterViewPagerDebets;
    private TabLayout slide_tab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_pager_debets = view.findViewById(R.id.view_pager_debets);
        slide_tab = view.findViewById(R.id.slide_tab);

        adapterViewPagerDebets = new AdapterViewPagerDebets(getFragmentManager(),getContext());
        view_pager_debets.setAdapter(adapterViewPagerDebets);
        slide_tab.setupWithViewPager(view_pager_debets);
    }
}
