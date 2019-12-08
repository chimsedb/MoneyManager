package com.example.moneymanager.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.moneymanager.R;
import com.example.moneymanager.ui.Fragment.debets.LoanDebetsFragment;
import com.example.moneymanager.ui.Fragment.debets.PayDebetsFragment;
import com.example.moneymanager.ui.Fragment.transaction.FutureFragment;
import com.example.moneymanager.ui.Fragment.transaction.NowFragment;
import com.example.moneymanager.ui.Fragment.transaction.PastFragment;

public class AdapterViewPagerDebets extends FragmentStatePagerAdapter {

    private Context context;

    public AdapterViewPagerDebets(@NonNull FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PayDebetsFragment();
            case 1:
                return new LoanDebetsFragment();
            default:
                return new PayDebetsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.txt_add_debets);
            case 1:
                return context.getString(R.string.txt_reduce_debets);
            default:
                return null;
        }
    }
}
