package com.hm.madroid.mood.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hm.madroid.mood.R;
import com.hm.madroid.mood.Utils;

public class AboutFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about,container,false) ;
        initView(view);
        return view ;
    }

    private void initView(View view){
        TextView version = (TextView) view.findViewById(R.id.about_version) ;
        String txt = version.getText() + "\n" + Utils.getVersionInfo(getActivity()) ;
        version.setText(txt);
    }

}
