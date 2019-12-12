package com.example.moneymanager.ui.Fragment.wallet;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterRCWallet;
import com.example.moneymanager.persistence.dao.transaction.LocalTransactionDataSource;
import com.example.moneymanager.persistence.dao.transaction.TransactionDataSource;
import com.example.moneymanager.persistence.dao.wallet.WalletDataSource;
import com.example.moneymanager.persistence.dao.wallet.WalletLocalDataSource;
import com.example.moneymanager.persistence.entities.Transaction;
import com.example.moneymanager.persistence.entities.Wallet;
import com.example.moneymanager.persistence.local.Database;
import com.example.moneymanager.ui.Fragment.transaction.ViewModelTransaction;
import com.example.moneymanager.ui.activity.add_wallet.AddWalletActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView rc_wallet;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ViewModelWallet viewModelWallet;

    private ViewModelTransaction transaction;
    private Wallet temp_wallet;
    private AdapterRCWallet adapterRCWallet;

    private TextView text_noti_1, text_noti_2;
    private List<Wallet> wallets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text_noti_1 = view.findViewById(R.id.text_noti_1);
        text_noti_2 = view.findViewById(R.id.text_noti_2);

        rc_wallet = view.findViewById(R.id.rc_wallet);
        rc_wallet.setHasFixedSize(true);
        rc_wallet.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        adapterRCWallet = new AdapterRCWallet();
        temp_wallet = new Wallet();
        temp_wallet.setCost("0 VND");

        viewModelWallet = ViewModelProviders.of(this).get(ViewModelWallet.class);
        viewModelWallet.setContext(getContext());
        viewModelWallet.getMutableLiveData().observe(this, new Observer<Wallet>() {
            @Override
            public void onChanged(Wallet wallet) {
                temp_wallet = wallet;
                wallets = new ArrayList<>();
                wallets.add(wallet);
                adapterRCWallet.submitList(wallets);
                rc_wallet.setAdapter(adapterRCWallet);

                if (wallets.size() != 0) {
                    text_noti_1.setText("");
                    text_noti_2.setText("");
                } else {
                    text_noti_1.setText(getContext().getString(R.string.txt_null_transaction));
                    text_noti_2.setText(getContext().getString(R.string.txt_null_transaction_small));
                }
            }
        });

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddWalletActivity.class));
            }
        });

        transaction = new ViewModelTransaction(getContext());
        transaction.getLiveDataTransaction().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                int total_cost_up = 0;
                int total_cost_down = 0;
                for (Transaction transaction : transactions) {
                    if (transaction.isStatusTransaction()) {
                        int value = Integer.parseInt(transaction.getWallet().substring(0, transaction.getWallet().lastIndexOf(" ")));
                        total_cost_up = total_cost_up + value;
                    } else {
                        int value = Integer.parseInt(transaction.getWallet().substring(0, transaction.getWallet().lastIndexOf(" ")));
                        total_cost_down = total_cost_down + value;
                    }
                }


                temp_wallet.setCost((Integer.parseInt(temp_wallet.getCost().substring(0, temp_wallet.getCost().lastIndexOf(" "))) - total_cost_down + total_cost_up) + " VND");

                adapterRCWallet.notifyDataSetChanged();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                nodeViewModel.delete(adapterRC.getNodeAt(viewHolder.getAdapterPosition()))

                WalletDataSource dataSource = new WalletLocalDataSource(Database.getInstance(getContext()).walletDAO());
                dataSource.deleteWallet(temp_wallet)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action() {
                            @Override
                            public void run() throws Exception {
                                Toasty.success(getContext(), "[XÓA THÀNH CÔNG VÍ TIỀN]", Toast.LENGTH_LONG).show();
                                text_noti_1.setText(getContext().getString(R.string.txt_null_transaction));
                                text_noti_2.setText(getContext().getString(R.string.txt_null_transaction_small));
                            }
                        });

                TransactionDataSource transactionDataSource = new LocalTransactionDataSource(Database.getInstance(getContext()).transactionDAO());
                transactionDataSource.deleteAllTransaction()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        });
            }

        }).attachToRecyclerView(rc_wallet);
    }

}
