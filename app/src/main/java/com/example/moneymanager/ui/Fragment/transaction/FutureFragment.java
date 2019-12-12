package com.example.moneymanager.ui.Fragment.transaction;

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
import com.example.moneymanager.persistence.entities.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FutureFragment extends Fragment {

    private ViewModelTransaction transaction;
    private RecyclerView rc_future;
    private List<Transaction> tempList;
    private TextView text_noti_1, text_noti_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_future, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text_noti_1 = view.findViewById(R.id.text_noti_1);
        text_noti_2 = view.findViewById(R.id.text_noti_2);

        rc_future = view.findViewById(R.id.rc_future);
        rc_future.setHasFixedSize(true);
        rc_future.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        transaction = new ViewModelTransaction(getContext());
        transaction.getLiveDataTransaction().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                tempList = new ArrayList<>();

                for (Transaction transaction : transactions) {
                    if (checkDay(transaction.getDateCreate())) {
                        tempList.add(transaction);
                    }
                }

                if (tempList.size() != 0) {
                    text_noti_1.setText("");
                    text_noti_2.setText("");
                } else {
                    text_noti_1.setText(getContext().getString(R.string.txt_null_transaction));
                    text_noti_2.setText(getContext().getString(R.string.txt_null_transaction_small));
                }
                rc_future.setAdapter(new AdapterRCNow(tempList));
            }
        });
    }

    private boolean checkDay(String event) {
        String month = event.substring(event.lastIndexOf(" ") + 4, event.lastIndexOf(" ") + 6);
        String year = "20" + event.substring(event.lastIndexOf(" ") + 7, event.lastIndexOf(" ") + 9);
        int tempDate = Integer.parseInt(month) + Integer.parseInt(year) * 12;

        Calendar calendar = Calendar.getInstance();
        int currentDate = calendar.get(Calendar.MONTH) + calendar.get(Calendar.YEAR) * 12 + 1;
        if (tempDate > currentDate) {
//            Log.d("12312311",tempDate+"");
            return true;
        }
        return false;
    }

}
