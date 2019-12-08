package com.example.moneymanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.ui.activity.login.LoginActivity;

public class FlashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Comon.CURRENT_USER == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(FlashScreen.this, LoginActivity.class));
                    finish();
                }
            }, 2000);
        }

    }
}
