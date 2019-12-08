package com.example.moneymanager.ui.Fragment.debets;


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
import android.widget.TextView;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterRCNow;
import com.example.moneymanager.persistence.Transaction;
import com.example.moneymanager.ui.Fragment.transaction.ViewModelTransaction;

import java.util.ArrayList;
import java.util.List;

public class LoanDebetsFragment extends Fragment {

    private ViewModelTransaction transaction;
    private RecyclerView rc_loan_debets;
    private List<Transaction> tempList;
    private TextView text_noti;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loan_debets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text_noti = view.findViewById(R.id.text_noti);

        rc_loan_debets = view.findViewById(R.id.rc_loan_debets);
        rc_loan_debets.setHasFixedSize(true);
        rc_loan_debets.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        transaction = new ViewModelTransaction(getContext());
        transaction.getLiveDataTransaction().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                tempList = new ArrayList<>();
                for (Transaction transaction : transactions){
                    if(transaction.isAddDebet()){
                        tempList.add(transaction);
                    }
                }
                if (tempList.size() != 0) {
                    text_noti.setText("");
                } else {
                    text_noti.setText(getContext().getString(R.string.txt_null_transaction));
                }
                rc_loan_debets.setAdapter(new AdapterRCNow(tempList));
            }
        });
    }
}
