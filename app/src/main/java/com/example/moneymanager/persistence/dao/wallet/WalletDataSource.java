package com.example.moneymanager.persistence.dao.wallet;

import com.example.moneymanager.persistence.entities.Wallet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface WalletDataSource {
    Flowable<Wallet> getWallet();

    Completable insertWallet(Wallet... wallet);

    Completable deleteWallet(Wallet wallet);
}
