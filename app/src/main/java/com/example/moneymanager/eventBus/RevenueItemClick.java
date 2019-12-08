package com.example.moneymanager.eventBus;

import com.example.moneymanager.model.Revenue;

public class RevenueItemClick {
    private boolean succes;
    private Revenue revenue;
    private boolean statusAddDebets;
    private boolean statusReduceDebets;
    private boolean statusTransaction;

    public RevenueItemClick(boolean succes, Revenue revenue, boolean statusAddDebets, boolean statusReduceDebets, boolean statusTransaction) {
        this.succes = succes;
        this.revenue = revenue;
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

    public Revenue getRevenue() {
        return revenue;
    }

    public void setRevenue(Revenue revenue) {
        this.revenue = revenue;
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
