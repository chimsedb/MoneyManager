package com.example.moneymanager.ui.Fragment.transaction;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterViewPagerTransaction;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends Fragment {


    private ViewPager view_pager_transaction;
    private AdapterViewPagerTransaction adapterViewPagerTransaction;
    private TabLayout slide_tab;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_pager_transaction = view.findViewById(R.id.view_pager_transaction);
        slide_tab = view.findViewById(R.id.slide_tab);

        adapterViewPagerTransaction = new AdapterViewPagerTransaction(getFragmentManager(),getContext());
        view_pager_transaction.setAdapter(adapterViewPagerTransaction);
        slide_tab.setupWithViewPager(view_pager_transaction);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

}
