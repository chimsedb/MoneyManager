package com.example.moneymanager.model;

public class TransactionStatistic {
    private Integer month;
    private Integer value;

    public TransactionStatistic() {
    }

    public TransactionStatistic(Integer month, Integer value) {
        this.month = month;
        this.value = value;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
