package com.example.moneymanager.eventBus;

import com.example.moneymanager.persistence.Transaction;

import java.util.List;

public class NowItemLongClick {
    private boolean succes;
    private List<Boolean> listStatus;
    private List<Transaction> transactions;

    public NowItemLongClick(boolean succes, List<Boolean> listStatus, List<Transaction> transactions) {
        this.succes = succes;
        this.listStatus = listStatus;
        this.transactions = transactions;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public List<Boolean> getListStatus() {
        return listStatus;
    }

    public void setListStatus(List<Boolean> listStatus) {
        this.listStatus = listStatus;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
