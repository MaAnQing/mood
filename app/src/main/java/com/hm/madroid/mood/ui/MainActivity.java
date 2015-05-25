package com.hm.madroid.mood.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.hm.madroid.mood.R;
import com.hm.madroid.mood.Utils;
import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;
import com.hm.madroid.mood.event.UpdateTitle;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


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

        init();

    }

    private void init(){
        if (!Utils.haveSDCard()){
            showLongToast(R.string.toast_no_sdcard);
        }
        initTitleBar();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(UpdateTitle title){
        updateTitle(title.getPosition());
    }

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
            case 3:
                fragment = new AboutFragment() ;
                loadFragment(R.id.container,fragment);
                break;
            case 4:
                startActivity(new Intent(this,FeedbackActivity.class));
                break;
            default:

                break;
        }

        //updateTitle(position);

    }

    private void updateTitle(int position){
        if (position == 0){
            mTitle.setText(R.string.title_record);
        }else if (position == 1){
            mTitle.setText(R.string.title_audio_list);
        }else if (position == 3){
            mTitle.setText(R.string.title_about);
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
        switch (v.getId()){
            case R.id.home :
                mDrawerLayout.openDrawer(R.id.navigation_drawer);
                break;
            default:
                break;
        }

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

        if (mDrawerLayout.isDrawerOpen(R.id.navigation_drawer)) {
            mDrawerLayout.closeDrawer(R.id.navigation_drawer);
        }else {
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
        }
        return false;
    }

}
