package com.example.moneymanager.ui.Fragment.transaction;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterRCNow;
import com.example.moneymanager.eventBus.CostItemClick;
import com.example.moneymanager.eventBus.NowItemClick;
import com.example.moneymanager.eventBus.NowItemLongClick;
import com.example.moneymanager.persistence.LocalTransactionDataSource;
import com.example.moneymanager.persistence.Transaction;
import com.example.moneymanager.persistence.TransactionDataSource;
import com.example.moneymanager.persistence.TransactionDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment implements MenuItem.OnMenuItemClickListener {

    private ViewModelTransaction transaction;
    private RecyclerView rc_now;
    private List<Transaction> tempList;
    private TextView text_noti_1, text_noti_2;
    private List<Transaction> tempTransactions;
    private List<Boolean> tempStatus;

    private Menu menu;
    private MenuItem item;

    private AdapterRCNow adapterRCNow;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_now, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tempTransactions = new ArrayList<>();
        tempStatus = new ArrayList<>();

        text_noti_1 = view.findViewById(R.id.text_noti_1);
        text_noti_2 = view.findViewById(R.id.text_noti_2);

        rc_now = view.findViewById(R.id.rc_now);
        rc_now.setHasFixedSize(true);
        rc_now.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
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
                adapterRCNow = new AdapterRCNow(tempList);
                rc_now.setAdapter(adapterRCNow);
            }
        });
    }

    private boolean checkDay(String event) {
        String month = event.substring(event.lastIndexOf(" ") + 4, event.lastIndexOf(" ") + 6);
        String year = "20" + event.substring(event.lastIndexOf(" ") + 7, event.lastIndexOf(" ") + 9);
        int tempDate = Integer.parseInt(month) + Integer.parseInt(year) * 12;

        Calendar calendar = Calendar.getInstance();
        int currentDate = calendar.get(Calendar.MONTH) + calendar.get(Calendar.YEAR) * 12 + 1;
        if (tempDate == currentDate) {
            Log.d("12312311",tempDate+" "+currentDate);
            return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        item = menu.findItem(R.id.action_settings);
        item.setIcon(R.drawable.ic_delete);
        item.setVisible(false);
        item.setOnMenuItemClickListener(this);
        this.menu = menu;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.d("123123", "123123");
                TransactionDataSource dataSource = new LocalTransactionDataSource(TransactionDatabase.getInstance(getContext()).transactionDAO());
                for (int i = 0; i < tempStatus.size(); i++) {
                    if (tempStatus.get(i).booleanValue() == true) {
                        compositeDisposable.add(dataSource.deleteTransaction(tempTransactions.get(i))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new BiConsumer() {
                                    @Override
                                    public void accept(Object o, Object o2) throws Exception {
                                        adapterRCNow.notifyDataSetChanged();
                                    }
                                }));
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        compositeDisposable.clear();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onNowItemClicked(NowItemClick event) {
        if (event.isSucces()) {
            for (Boolean check : event.getListStatus()) {
                if (check == true) {
                    item.setVisible(true);
                    return;
                }
            }
            item.setVisible(false);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onNowLongItemClicked(NowItemLongClick event) {
        if (event.isSucces()) {
            tempStatus = event.getListStatus();
            tempTransactions = event.getTransactions();
            for (Boolean check : event.getListStatus()) {
                if (check == true) {
                    item.setVisible(true);
                    return;
                }
            }
            item.setVisible(false);
        }
    }


}
