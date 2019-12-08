package com.example.moneymanager.ui.activity.add;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.moneymanager.R;
import com.example.moneymanager.adapter.AdapterViewPagerService;
import com.example.moneymanager.eventBus.CostItemClick;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ServiceActivity extends AppCompatActivity {

    private Toolbar toolbar_service;
    private TabLayout slide_tab_service;
    private ViewPager viewpager_service;

    private AdapterViewPagerService adapterViewPagerService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        slide_tab_service = findViewById(R.id.slide_tab_service);
        viewpager_service = findViewById(R.id.viewpager_service);

        toolbar_service = findViewById(R.id.toolbar_service);
        toolbar_service.setNavigationIcon(R.drawable.ic_arrow_back_black);
        toolbar_service.setTitle(getApplicationContext().getString(R.string.title_service));

        setSupportActionBar(toolbar_service);
        toolbar_service.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapterViewPagerService = new AdapterViewPagerService(getSupportFragmentManager(), getApplicationContext());
        viewpager_service.setAdapter(adapterViewPagerService);
        slide_tab_service.setupWithViewPager(viewpager_service);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service, menu);
        return true;
    }

}
