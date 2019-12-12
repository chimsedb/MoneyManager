package com.example.moneymanager.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Wallet")
public class Wallet {


    @ColumnInfo(name = "cost")
    private String cost;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    private String name;

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
