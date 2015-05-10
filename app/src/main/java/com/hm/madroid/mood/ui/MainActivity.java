package com.hm.madroid.mood.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import com.hm.madroid.mood.R;
import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,View.OnClickListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private TextView mTitle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout) ;

        initTitleBar();
       // initDB();
    }


    private  void initDB(){
        AudioInfoManager manager = AudioInfoManager.getInstance() ;
        List<AudioInfo> infos = new ArrayList<>() ;
        for (int i =0 ; i<=3 ;i++){
            AudioInfo info = new AudioInfo() ;
            info.address = "address" + i ;
            info.date = "DATE" + i ;
            info.duration = "duration" + i ;
            info.mood = i ;
            info.name = "name" + i ;
            infos.add(info);
        }
        manager.addInfos(infos) ;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        EventBus.getDefault().unregister(this);
//        super.onStop();
//    }
//
//    public void onEvent(){
//
//    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment ;
        switch (position){
            case 0:
                fragment = new RecordFragment() ;
                loadFragment(R.id.container, fragment);
                break;
            case 1:
                fragment = new AudioListFragment() ;
                loadFragment(R.id.container, fragment);
                break;
            case 2:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case 4:
                startActivity(new Intent(this,FeedbackActivity.class));
                break;
            default:
                fragment = new AudioListFragment() ;
                loadFragment(R.id.container, fragment);
                break;
        }

    }

    private void initTitleBar(){
        findViewById(R.id.home_back).setVisibility(View.GONE);
        mTitle = (TextView)findViewById(R.id.home) ;
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(R.string.title_record);
        mTitle.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        mDrawerLayout.openDrawer(R.id.navigation_drawer);
    }

    public void onSectionAttached(int number) {
        String[] s = getResources().getStringArray(R.array.menu_list) ;
        //mTitle = s[number-1] ;
    }


    // 双击退出
    boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                showToast(getString(R.string.toast_exit));
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }

}
