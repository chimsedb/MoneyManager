package com.example.moneymanager.ui.Fragment.transaction;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanager.persistence.dao.transaction.LocalTransactionDataSource;
import com.example.moneymanager.persistence.entities.Transaction;
import com.example.moneymanager.persistence.dao.transaction.TransactionDataSource;
import com.example.moneymanager.persistence.local.Database;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewModelTransaction extends ViewModel {
    private MutableLiveData<List<Transaction>> liveDataTransaction;
    private TransactionDataSource dataSource;

    public ViewModelTransaction(Context context) {
        dataSource = new LocalTransactionDataSource(Database.getInstance(context).transactionDAO());
    }

    public MutableLiveData<List<Transaction>> getLiveDataTransaction() {
        if(liveDataTransaction == null){
            liveDataTransaction = new MutableLiveData<>();
        }
        getTransaction();
        return liveDataTransaction;
    }

    private void getTransaction() {
        dataSource.getAllTransaction()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Transaction>>() {
                    @Override
                    public void accept(List<Transaction> transactions) throws Exception {
                        liveDataTransaction.setValue(transactions);
                    }
                });
    }
}
