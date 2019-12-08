package com.example.moneymanager.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.moneymanager.R;
import com.example.moneymanager.ui.Fragment.transaction.FutureFragment;
import com.example.moneymanager.ui.Fragment.transaction.NowFragment;
import com.example.moneymanager.ui.Fragment.transaction.PastFragment;

public class AdapterViewPagerTransaction extends FragmentStatePagerAdapter {
    private Context context;

    public AdapterViewPagerTransaction(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PastFragment();
            case 1:
                return new NowFragment();
            case 2:
                return new FutureFragment();
            default:
                return new NowFragment();
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
                return context.getString(R.string.past);
            case 1:
                return context.getString(R.string.now);
            case 2:
                return context.getString(R.string.future);
            default:
                return null;
        }
    }
}
