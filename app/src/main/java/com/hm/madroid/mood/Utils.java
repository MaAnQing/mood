package com.hm.madroid.mood;

import android.os.Environment;

/**
 * Created by madroid on 15-5-11.
 */
public class Utils {

    public static boolean haveSDCard(){

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ;
    }

    public static String getSDCardPath(){
        String path = "" ;
        if (haveSDCard()){
            path = Environment.getExternalStorageDirectory().getPath() ;
        }
        return path ;
    }

}

