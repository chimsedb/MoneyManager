package com.example.moneymanager.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TransactionDAO {

    @Query("SELECT * FROM `Transaction` ")
    Flowable<List<Transaction>> getAllTransaction();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertTransaction(Transaction... transaction);

    @Delete
    Single<Integer> deleteTransaction(Transaction transaction);
}
