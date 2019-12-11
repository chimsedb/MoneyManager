package com.example.moneymanager.eventBus;

import java.util.List;

public class NowItemClick {
    private boolean succes;
    private List<Boolean> listStatus;

    public NowItemClick(boolean succes, List<Boolean> listStatus) {
        this.succes = succes;
        this.listStatus = listStatus;
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
}
