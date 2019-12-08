package com.example.moneymanager.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transaction")
public class Transaction {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transactionId")
    private int id;

    @ColumnInfo(name = "wallet")
    private String wallet;

    @ColumnInfo(name = "service")
    private String service;

    @ColumnInfo(name = "image")
    private byte[] image;

    @ColumnInfo(name = "note")
    private String note;

    @ColumnInfo(name = "dateCreate")
    private String dateCreate;

    @ColumnInfo(name = "anyone")
    private String anyone;

    @ColumnInfo(name = "event")
    private String event;

    @ColumnInfo(name = "alarm")
    private String alarm;

    @ColumnInfo(name = "addDebet")
    private boolean addDebet;

    @ColumnInfo(name = "reduceDebet")
    private boolean reduceDebet;

    @ColumnInfo(name = "statusTransaction")
    private boolean statusTransaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getAnyone() {
        return anyone;
    }

    public void setAnyone(String anyone) {
        this.anyone = anyone;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public boolean isAddDebet() {
        return addDebet;
    }

    public void setAddDebet(boolean addDebet) {
        this.addDebet = addDebet;
    }

    public boolean isReduceDebet() {
        return reduceDebet;
    }

    public void setReduceDebet(boolean reduceDebet) {
        this.reduceDebet = reduceDebet;
    }

    public boolean isStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(boolean statusTransaction) {
        this.statusTransaction = statusTransaction;
    }
}
