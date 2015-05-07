package com.hm.madroid.mood;

import android.app.Application;
import android.content.Context;


/**
 * Created by madroid on 15-4-27.
 */
public class MoodApp extends Application {

    private Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this ;
        //ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //ActiveAndroid.dispose();
    }
}
