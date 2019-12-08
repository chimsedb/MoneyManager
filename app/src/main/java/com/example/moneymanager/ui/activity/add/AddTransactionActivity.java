package com.example.moneymanager.ui.activity.add;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.R;
import com.example.moneymanager.eventBus.CostItemClick;
import com.example.moneymanager.eventBus.DebetItemClick;
import com.example.moneymanager.eventBus.RevenueItemClick;
import com.example.moneymanager.persistence.LocalTransactionDataSource;
import com.example.moneymanager.persistence.Transaction;
import com.example.moneymanager.persistence.TransactionDataSource;
import com.example.moneymanager.persistence.TransactionDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AddTransactionActivity extends AppCompatActivity {
    private Toolbar toolbar_add;
    private EditText et_category_service, et_note, et_cost, et_friend, et_event_add, et_wallet, et_event, et_clock_add;
    private ImageView img_category_service;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        toolbar_add = findViewById(R.id.toolbar_add);
        toolbar_add.setNavigationIcon(R.drawable.ic_arrow_back_black);
        toolbar_add.setTitle(getApplicationContext().getString(R.string.title_add_toolbar));

        img_category_service = findViewById(R.id.img_category_service);

        et_event = findViewById(R.id.et_event);
        et_clock_add = findViewById(R.id.et_clock_add);
        et_category_service = findViewById(R.id.et_category_service);
        et_cost = findViewById(R.id.et_cost);
        et_cost.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et_wallet = findViewById(R.id.et_wallet);
        et_wallet.setText(Comon.CURRENT_USER.getName());
        et_note = findViewById(R.id.et_note);
        et_note.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et_friend = findViewById(R.id.et_friend);
        et_friend.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et_event_add = findViewById(R.id.et_event_add);
        et_event_add.setImeOptions(EditorInfo.IME_ACTION_DONE);

        setOnclick();
        setSupportActionBar(toolbar_add);
        toolbar_add.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private DatePickerDialog.OnDateSetListener createDatePicker(final Calendar calendar, final EditText editText) {
        return new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                updateEditText(calendar, editText);
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateEditText(Calendar calendar, EditText editText) {
        String myFormat = "EEEE , dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, getResources().getConfiguration().locale);

        editText.setText(sdf.format(calendar.getTime()));
    }

    private void setOnclick() {

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


        et_category_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(AddTransactionActivity.this, ServiceActivity.class), Comon.REQUEST_ADD);
            }
        });

        et_event.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();

            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTransactionActivity.this, createDatePicker(calendar, et_event)
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        et_clock_add.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();

            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTransactionActivity.this, createDatePicker(calendar, et_clock_add)
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
                TransactionDataSource dataSource = new LocalTransactionDataSource(TransactionDatabase.getInstance(getApplicationContext()).transactionDAO());
                compositeDisposable.add(dataSource.insertTransaction(getTransaction())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action() {
                            @Override
                            public void run() throws Exception {
                                Toasty.success(getApplicationContext(),"[INSERTED]",Toasty.LENGTH_LONG).show();
                            }
                        }));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setWallet(et_cost.getText().toString());
        transaction.setService(et_category_service.getText().toString());

        Bitmap bitmap = ((BitmapDrawable)img_category_service.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imgBitmap = stream.toByteArray();
        transaction.setImage(imgBitmap);

        transaction.setNote(et_note.getText().toString());
        transaction.setDateCreate(et_event.getText().toString());
        transaction.setAnyone(et_friend.getText().toString());
        transaction.setEvent(et_event_add.getText().toString());
        transaction.setAlarm(et_clock_add.getText().toString());
        transaction.setAddDebet(Comon.STATUS_ADD_DEBET);
        transaction.setReduceDebet(Comon.STATUS_REDUCE_DEBET);
        transaction.setStatusTransaction(Comon.STATUS_TRANSACTION);
        return transaction;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        compositeDisposable.clear();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCostItemClicked(CostItemClick event) {
        if (event.isSucces()) {
            et_wallet.setText(Comon.CURRENT_USER.getName());
            img_category_service.setImageResource(event.getCost().getImage());
            et_category_service.setText(event.getCost().getName());
            Comon.STATUS_ADD_DEBET = event.isStatusAddDebets();
            Comon.STATUS_REDUCE_DEBET = event.isStatusReduceDebets();
            Comon.STATUS_TRANSACTION = event.isStatusTransaction();
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDebetItemClicked(DebetItemClick event) {
        if (event.isSucces()) {
            et_wallet.setText(Comon.CURRENT_USER.getName());
            img_category_service.setImageResource(event.getDebet().getImage());
            et_category_service.setText(event.getDebet().getName());
            Comon.STATUS_ADD_DEBET = event.isStatusAddDebets();
            Comon.STATUS_REDUCE_DEBET = event.isStatusReduceDebets();
            Comon.STATUS_TRANSACTION = event.isStatusTransaction();
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRevenueItemClicked(RevenueItemClick event) {
        if (event.isSucces()) {
            et_wallet.setText(Comon.CURRENT_USER.getName());
            img_category_service.setImageResource(event.getRevenue().getImage());
            et_category_service.setText(event.getRevenue().getName());
            Comon.STATUS_ADD_DEBET = event.isStatusAddDebets();
            Comon.STATUS_REDUCE_DEBET = event.isStatusReduceDebets();
            Comon.STATUS_TRANSACTION = event.isStatusTransaction();
        }
    }
}


