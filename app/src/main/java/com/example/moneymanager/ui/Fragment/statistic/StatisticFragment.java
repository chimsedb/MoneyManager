package com.example.moneymanager.ui.Fragment.statistic;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterRcStatistic;
import com.example.moneymanager.model.TransactionStatistic;
import com.example.moneymanager.persistence.entities.Transaction;
import com.example.moneymanager.ui.Fragment.transaction.ViewModelTransaction;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {

    private ViewModelTransaction transaction;
    private List<TransactionStatistic> up;
    private List<TransactionStatistic> down;
    private BarChart bar_statistic;
    private RecyclerView rc_statistic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bar_statistic = view.findViewById(R.id.bar_statistic);
        rc_statistic = view.findViewById(R.id.rc_statistic);
        rc_statistic.setHasFixedSize(true);
        rc_statistic.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        up = new ArrayList<>();
        down = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            up.add(new TransactionStatistic(i + 1, 0));
            down.add(new TransactionStatistic(i + 1, 0));
        }

        transaction = new ViewModelTransaction(getContext());
        transaction.getLiveDataTransaction().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {

                for (Transaction transaction : transactions) {
                    if (transaction.isStatusTransaction()) {
                        int month = Integer.parseInt(transaction.getDateCreate().substring(transaction.getDateCreate().lastIndexOf(" ") + 4, transaction.getDateCreate().lastIndexOf(" ") + 6));
                        int value = Integer.parseInt(transaction.getWallet().substring(0, transaction.getWallet().lastIndexOf(" ")));
                        up.get(month - 1).setValue(up.get(month - 1).getValue() + value);
                    } else {
                        int month = Integer.parseInt(transaction.getDateCreate().substring(transaction.getDateCreate().lastIndexOf(" ") + 4, transaction.getDateCreate().lastIndexOf(" ") + 6));
                        int value = Integer.parseInt(transaction.getWallet().substring(0, transaction.getWallet().lastIndexOf(" ")));
                        down.get(month - 1).setValue(down.get(month - 1).getValue() + value);
                    }
                }
                createBarChart();
                List<TransactionStatistic> temp = new ArrayList<>();
                for (int i = 0; i < up.size(); i++) {
                    TransactionStatistic transactionStatistic = new TransactionStatistic();
                    transactionStatistic.setMonth(up.get(i).getMonth());
                    transactionStatistic.setValue(up.get(i).getValue() - down.get(i).getValue());
                    temp.add(transactionStatistic);
                }
                rc_statistic.setAdapter(new AdapterRcStatistic(temp));
            }
        });


    }

    private void createBarChart() {
        List<BarEntry> entriesTransaction = new ArrayList<>();
        for (int i = 0; i < up.size(); i++) {
            entriesTransaction.add(new BarEntry((i + 1) * 3, up.get(i).getValue()));
        }
        List<BarEntry> entriesDebet = new ArrayList<>();
        for (int i = 0; i < down.size(); i++) {
            entriesDebet.add(new BarEntry((i + 1) * 3 + 1, down.get(i).getValue()));
        }

        BarDataSet bardatasetTransaction = new BarDataSet(entriesTransaction, "Dòng tiền thu");
        bardatasetTransaction.setColor(Color.BLUE);
        BarDataSet bardatasetDebet = new BarDataSet(entriesDebet, "Dòng tiền chi");
        bardatasetDebet.setColor(Color.RED);

        BarData data = new BarData(bardatasetTransaction, bardatasetDebet);
        bar_statistic.setData(data);
        bar_statistic.getDescription().setEnabled(false);
        bar_statistic.animateY(2000);
    }
}
