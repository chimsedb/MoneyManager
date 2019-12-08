package com.example.moneymanager.eventBus;

import com.example.moneymanager.model.Cost;

public class CostItemClick {
    private boolean succes;
    private Cost cost;
    private boolean statusAddDebets;
    private boolean statusReduceDebets;
    private boolean statusTransaction;

    public CostItemClick(boolean succes, Cost cost, boolean statusAddDebets, boolean statusReduceDebets, boolean statusTransaction) {
        this.succes = succes;
        this.cost = cost;
        this.statusAddDebets = statusAddDebets;
        this.statusReduceDebets = statusReduceDebets;
        this.statusTransaction = statusTransaction;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public boolean isStatusAddDebets() {
        return statusAddDebets;
    }

    public void setStatusAddDebets(boolean statusAddDebets) {
        this.statusAddDebets = statusAddDebets;
    }

    public boolean isStatusReduceDebets() {
        return statusReduceDebets;
    }

    public void setStatusReduceDebets(boolean statusReduceDebets) {
        this.statusReduceDebets = statusReduceDebets;
    }

    public boolean isStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(boolean statusTransaction) {
        this.statusTransaction = statusTransaction;
    }
}
