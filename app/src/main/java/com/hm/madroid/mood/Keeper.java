package com.hm.madroid.mood;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by madroid on 15-5-11.
 */
public class Keeper {

    private static final String TAG  = "Keeper" ;

    private static SharedPreferences sp ;



    public static void init(Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context) ;
    }

    public static int readAudioFormat(){
        return Integer.valueOf(sp.getString("audio_format", "4")) ;
    }

    public static boolean readRecordPhone(){
        return sp.getBoolean("record_phone", false);
    }

    public static boolean readRecordChannel(){
        return sp.getBoolean("record_channel", false);
    }

    public static int readRecordSampleRate(){
        return Integer.valueOf(sp.getString("record_sample_rate", "441000")) ;
    }

    public static int readAutoDelete(){
        return Integer.valueOf(sp.getString("auto_delete", "0")) ;
    }

    public static boolean isLogin(){
        return sp.getBoolean("login", false) ;
    }

    public static void keepLogin(Boolean login){
        SharedPreferences.Editor editor = sp.edit() ;
        editor.putBoolean("login",login) ;
        editor.commit() ;
    }


}
