package com.example.moneymanager.eventBus;

import com.example.moneymanager.model.Debet;

public class DebetItemClick {
    private boolean succes;
    private Debet debet;
    private boolean statusAddDebets;
    private boolean statusReduceDebets;
    private boolean statusTransaction;

    public DebetItemClick(boolean succes, Debet debet, boolean statusAddDebets, boolean statusReduceDebets, boolean statusTransaction) {
        this.succes = succes;
        this.debet = debet;
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

    public Debet getDebet() {
        return debet;
    }

    public void setDebet(Debet debet) {
        this.debet = debet;
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
