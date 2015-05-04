package com.hm.madroid.mood.ui;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hm.madroid.mood.R;

public class BaseActivity extends ActionBarActivity {

    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext() ;
    }

    public void ShowToast(String s){
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }

    public void ShowLongToast(String s){
        Toast.makeText(mContext,s,Toast.LENGTH_LONG).show();
    }

}
