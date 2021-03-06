package com.hm.madroid.mood;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by madroid on 15-5-11.
 */
public class Utils {

    private static String TAG = "Utils" ;

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

    public static String getDuration(long s){
        s = s / 1000 ;
        if (s > 60){
            if (s / 60 < 10){
                return  "0" + (int)s / 60 + ":" + (int) s % 60 ;
            }
            return  (int)s / 60 + ":" + (int) s % 60 ;
        }else {
            return "00:" + (int) s % 60 ;
        }
    }

    public static final String formatDate(Long timestamp , String format) {
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(format) ;
        return simpleDateFormat.format(timestamp) ;
    }

    public static String getDate(Long timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd") ;
        return simpleDateFormat.format(new Date(timestamp)) ;
    }

    public static String getAddress(){
        return "anHui hefei" ;
    }

    public static int getMood(){
        Random random = new Random() ;
        return random.nextInt(4)%(4-1+1) + 1 ;
    }

    public static String getMoodStr(){
        Random random = new Random() ;
        int mood = random.nextInt(4)%(4-1+1) + 1 ;
        String moodStr = "" ;
        switch (mood){
            case Constant.MOOD_ANGRY:
                moodStr = "angry" ;
                break;
            case Constant.MOOD_SAD:
                moodStr = "sad" ;
                break;
            case Constant.MOOD_HAPPY:
                moodStr = "happy" ;
                break;
            case Constant.MOOD_NEUTRAL:
                moodStr = "neutral" ;
                break;
            default:
                moodStr = "neutral" ;
        }
        return moodStr ;
    }

    public static String getMoodStr(int mood){
        String moodStr = "" ;
        switch (mood){
            case Constant.MOOD_ANGRY:
                moodStr = "angry" ;
                break;
            case Constant.MOOD_SAD:
                moodStr = "sad" ;
                break;
            case Constant.MOOD_HAPPY:
                moodStr = "happy" ;
                break;
            case Constant.MOOD_NEUTRAL:
                moodStr = "neutral" ;
                break;
            default:
                moodStr = "neutral" ;
        }
        return moodStr ;
    }

    public static String formatTime(long date){
        String time = "" ;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss") ;
        time = format.format(date) ;
        return time ;
    }

    public static String transFormatStr(String file, String suffix) {
        Log.i(TAG, "file path : " +file) ;
        //以 . 分割字符串
        String path[] = file.split("\\.") ;
        Log.i(TAG, "new file path : " + path[0] + suffix) ;
        return path[0] + suffix ;
    }

    public static final String getVersionInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            Toast.makeText(context, "版本号：" + pi.versionName, Toast.LENGTH_LONG).show();
            return "版本号：" + pi.versionName ;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "版本号：v1.0" ;
        }
    }
}

