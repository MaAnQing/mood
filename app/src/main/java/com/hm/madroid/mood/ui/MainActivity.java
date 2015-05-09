package com.hm.madroid.mood.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.hm.madroid.mood.R;
import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        initDB();
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
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case 2:
                fragment = new AudioListFragment() ;
                loadFragment(R.id.container, fragment);
                break;
            case 3:
                fragment = new SettingFragment() ;
                loadFragment(R.id.container, fragment);
                break;
            case 5:
                startActivity(new Intent(this,FeedbackActivity.class));
                break;
            default:
                fragment = new AudioListFragment() ;
                loadFragment(R.id.container, fragment);
                break;
        }

    }

    public void onSectionAttached(int number) {
        String[] s = getResources().getStringArray(R.array.menu_list) ;
        //mTitle = s[number-1] ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

}
