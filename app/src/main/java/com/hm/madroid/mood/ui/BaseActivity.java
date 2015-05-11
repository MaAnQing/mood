package com.hm.madroid.mood.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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

    public void showToast(String s){
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String s){
        Toast.makeText(mContext,s,Toast.LENGTH_LONG).show();
    }

    public void loadFragment(int container,Fragment fragment){
        getFragmentManager().beginTransaction().replace(container, fragment).addToBackStack(fragment.getClass().getName()).commit();
    }


}
