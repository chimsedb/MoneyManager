package com.example.moneymanager.persistence.dao.wallet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moneymanager.persistence.entities.Transaction;
import com.example.moneymanager.persistence.entities.Wallet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface WalletDAO {

    @Query("SELECT * FROM Wallet")
    Flowable<Wallet> getWallet();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertWallet(Wallet... wallet);

    @Delete
    Completable deleteWallet(Wallet wallet);
}
