package com.example.moneymanager.ui.activity.add_wallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.R;
import com.example.moneymanager.persistence.dao.wallet.WalletDataSource;
import com.example.moneymanager.persistence.dao.wallet.WalletLocalDataSource;
import com.example.moneymanager.persistence.entities.Wallet;
import com.example.moneymanager.persistence.local.Database;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AddWalletActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText et_cost, et_wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);

        et_cost = findViewById(R.id.et_cost);
        et_wallet = findViewById(R.id.et_wallet);
        et_wallet.setText(Comon.CURRENT_USER.getName());

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);

        toolbar.setTitle(getString(R.string.menu_wallet));
        setSupportActionBar(toolbar);

        setOnClick();

    }

    private void setOnClick() {
        et_cost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    et_cost.setText("");
                }
            }
        });

        et_cost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!et_cost.getText().toString().contains("VND")) {
                        String result = et_cost.getText().toString() + " VND";
                        et_cost.setText(result);
                    }
                }
                return false;
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (et_cost.getText().toString().contains("VND")) {
                    Wallet wallet = new Wallet();
                    wallet.setCost(et_cost.getText().toString());
                    wallet.setName(et_wallet.getText().toString());
                    WalletDataSource dataSource = new WalletLocalDataSource(Database.getInstance(getApplicationContext()).walletDAO());
                    dataSource.insertWallet(wallet)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Action() {
                                @Override
                                public void run() throws Exception {
                                    Toasty.success(getApplicationContext(), "[TẠO THÀNH CÔNG]").show();
                                }
                            });
                    finish();
                }
                return true;
        }
        return false;
    }
}
