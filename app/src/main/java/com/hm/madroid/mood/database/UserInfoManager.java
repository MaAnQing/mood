package com.hm.madroid.mood.database;


import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by madroid on 15-5-7.
 */
public class UserInfoManager {

    private UserInfoManager manager ;
    private static final String TAG = "UserInfoManager" ;

    private UserInfoManager(){

    }

    public UserInfoManager getInstance(){
        if (manager == null){
            manager = new UserInfoManager() ;
        }

        return manager ;
    }


    //add
    public boolean addInfo(UserInfo info){
        boolean success = false ;

        info.save();

        success = true ;

        return success ;
    }

    public boolean addInfos(List<UserInfo> infos){

        boolean success = false ;

        ActiveAndroid.beginTransaction();
        try {
            for (UserInfo info : infos) {
                addInfo(info);
            }
            ActiveAndroid.setTransactionSuccessful();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            ActiveAndroid.endTransaction();
        }

        return success ;
    }

    //delete
    public void deteleInfo(UserInfo info){
        info.delete();
    }


    public void deteleInfos(List<UserInfo> infos){
        ActiveAndroid.beginTransaction();
        try{
            for (UserInfo info: infos){
                deteleInfo(info);
            }
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }
    }

    //edit

    //query
    public AudioInfo getInfo (int userId){
        return new Select().from(UserInfo.class).where("UserId = ?",userId).executeSingle() ;
    }

    public List<AudioInfo> getInfos (int userId){
        return new Select().from(UserInfo.class).where("UserId = ?",userId).execute() ;
    }

    public List<UserInfo> getAllInfos(){
        List<UserInfo> infos= new Select().all().from(UserInfo.class).execute() ;

        for (UserInfo fileInfos : infos){
            Log.i(TAG,"fileinfo :" + fileInfos.toString()) ;
        }
        return infos;
    }

    public AudioInfo getRandom() {
        return new Select().from(UserInfo.class).orderBy("RANDOM()").executeSingle();
    }
}
