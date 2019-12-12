package com.example.moneymanager.persistence.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moneymanager.persistence.dao.transaction.TransactionDAO;
import com.example.moneymanager.persistence.dao.wallet.WalletDAO;
import com.example.moneymanager.persistence.entities.Transaction;
import com.example.moneymanager.persistence.entities.Wallet;

@androidx.room.Database(entities = {Transaction.class, Wallet.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract TransactionDAO transactionDAO();
    public abstract WalletDAO walletDAO();

    private static Database instance;

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, Database.class, "TransactionV2").build();
        }
        return instance;
    }
}
