package com.example.moneymanager.ui.Fragment.wallet;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanager.persistence.dao.wallet.WalletDataSource;
import com.example.moneymanager.persistence.dao.wallet.WalletLocalDataSource;
import com.example.moneymanager.persistence.entities.Wallet;
import com.example.moneymanager.persistence.local.Database;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewModelWallet extends ViewModel {
    private MutableLiveData<Wallet> mutableLiveData;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MutableLiveData<Wallet> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        getWallet();
        return mutableLiveData;
    }

    private void getWallet() {
        Log.d("getWallet","123123");
        WalletDataSource dataSource = new WalletLocalDataSource(Database.getInstance(getContext()).walletDAO());
        dataSource.getWallet()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Wallet>() {
                    @Override
                    public void accept(Wallet wallet) throws Exception {
                        mutableLiveData.setValue(wallet);
                    }
                });
    }
}
