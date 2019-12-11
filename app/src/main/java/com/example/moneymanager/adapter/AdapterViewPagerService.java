package com.example.moneymanager.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.moneymanager.R;
import com.example.moneymanager.ui.Fragment.service.CostFragment;
import com.example.moneymanager.ui.Fragment.service.DebetsFragment;
import com.example.moneymanager.ui.Fragment.service.RevenueFragment;

public class AdapterViewPagerService extends FragmentStatePagerAdapter {
    private Context context;

    public AdapterViewPagerService(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DebetsFragment();
            case 1:
                return new CostFragment();
            case 2:
                return new RevenueFragment();
            default:
                return new CostFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.debets);
            case 1:
                return context.getString(R.string.cost);
            case 2:
                return context.getString(R.string.revenue);
            default:
                return null;
        }
    }
}
