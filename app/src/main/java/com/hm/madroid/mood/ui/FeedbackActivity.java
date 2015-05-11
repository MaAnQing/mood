package com.hm.madroid.mood.ui;

import android.os.Bundle;

import com.hm.madroid.mood.R;

public class FeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        loadFragment(R.id.container,new FeedbackFragment());
    }

}
