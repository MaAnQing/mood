package com.hm.madroid.mood.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.hm.madroid.mood.R;

public class SettingFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_setting);
        init() ;
    }

    private void init(){

    }


}
