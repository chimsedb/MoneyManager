package com.example.moneymanager.persistence;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalTransactionDataSource implements TransactionDataSource {

    private TransactionDAO transactionDAO;

    public LocalTransactionDataSource(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public Flowable<List<Transaction>> getAllTransaction() {
        return transactionDAO.getAllTransaction();
    }

    @Override
    public Completable insertTransaction(Transaction... transaction) {
        return transactionDAO.insertTransaction(transaction);
    }
}
