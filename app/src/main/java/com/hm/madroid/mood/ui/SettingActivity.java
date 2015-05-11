package com.hm.madroid.mood.ui;

import android.os.Bundle;
import android.view.View;

import com.hm.madroid.mood.R;

public class SettingActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initTitleBar();
        loadFragment(R.id.container,new SettingFragment());
    }

    private void initTitleBar(){
        View backHome = findViewById(R.id.home_back) ;
        backHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
