package com.example.moneymanager.persistence.dao.transaction;

import com.example.moneymanager.persistence.entities.Transaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface TransactionDataSource {

    Flowable<List<Transaction>> getAllTransaction();

    Completable insertTransaction(Transaction... transaction);

    Single<Integer> deleteTransaction(Transaction transaction);

    Completable deleteAllTransaction();
}
