package com.example.moneymanager.persistence.dao.wallet;

import com.example.moneymanager.persistence.entities.Wallet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WalletLocalDataSource implements WalletDataSource{

    private WalletDAO walletDAO;

    public WalletLocalDataSource(WalletDAO walletDAO) {
        this.walletDAO = walletDAO;
    }

    @Override
    public Flowable<Wallet> getWallet() {
        return walletDAO.getWallet();
    }

    @Override
    public Completable insertWallet(Wallet... wallet) {
        return walletDAO.insertWallet(wallet);
    }

    @Override
    public Completable deleteWallet(Wallet wallet) {
        return walletDAO.deleteWallet(wallet);
    }
}
