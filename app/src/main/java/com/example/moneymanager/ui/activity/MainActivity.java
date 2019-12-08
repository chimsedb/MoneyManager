package com.example.moneymanager.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.R;
import com.example.moneymanager.model.User;
import com.example.moneymanager.ui.Fragment.statistic.StatisticFragment;
import com.example.moneymanager.ui.Fragment.debets.DebetFragment;
import com.example.moneymanager.ui.Fragment.transaction.TransactionFragment;
import com.example.moneymanager.ui.activity.add.AddTransactionActivity;
import com.example.moneymanager.ui.activity.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private FragmentManager manager;
    private FloatingActionButton fab;
    private NavigationView navigationView;
    private TextView txt_title_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txt_title_header = headerView.findViewById(R.id.txt_title_header);
        txt_title_header.setText("Hi! "+Comon.CURRENT_USER.getName());

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.menu_transactions));
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layout_main, new TransactionFragment(), "transaction");
        transaction.commit();

        handleEvent();

    }

    private void handleEvent() {
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddTransactionActivity.class), Comon.REQUEST_ADD);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_transactions) {
            if (manager.findFragmentByTag("transaction") == null) {
                FragmentTransaction transaction = manager.beginTransaction();
                toolbar.setTitle(getString(R.string.menu_transactions));
                transaction.replace(R.id.layout_main, new TransactionFragment());
                transaction.commit();
                fab.setVisibility(View.VISIBLE);
            }
            toolbar.setTitle(getString(R.string.menu_transactions));
        } else if (id == R.id.nav_budgets) {
            toolbar.setTitle(getString(R.string.menu_budgets));
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_main, new StatisticFragment());
            transaction.commit();
            fab.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_debts) {
            toolbar.setTitle(getString(R.string.menu_debts));
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.layout_main, new DebetFragment());
            transaction.commit();
            fab.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_sign_out) {
            Comon.CURRENT_USER = null;
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
