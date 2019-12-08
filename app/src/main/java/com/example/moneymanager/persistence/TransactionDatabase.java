package com.example.moneymanager.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class}, version = 1, exportSchema = false)
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract TransactionDAO transactionDAO();

    private static TransactionDatabase instance;

    public static TransactionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TransactionDatabase.class, "TransactionV2").build();
        }
        return instance;
    }
}
