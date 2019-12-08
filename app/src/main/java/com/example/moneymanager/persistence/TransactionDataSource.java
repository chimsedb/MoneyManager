package com.example.moneymanager.persistence;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface TransactionDataSource {

    Flowable<List<Transaction>> getAllTransaction();

    Completable insertTransaction(Transaction... transaction);
}
