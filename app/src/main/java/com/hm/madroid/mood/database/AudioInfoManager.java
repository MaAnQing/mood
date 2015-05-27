package com.hm.madroid.mood.database;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.List;


/**
 * Created by madroid on 15-4-27.
 */

public class AudioInfoManager {

    private static final String TAG = "FileInfoManager" ;

    private static AudioInfoManager manager ;

    public AudioInfoManager(){

    }

    public static AudioInfoManager getInstance(){
        if (manager ==null){
            manager = new AudioInfoManager() ;
        }
        return manager ;
    }

    //add
    public boolean addInfo(AudioInfo info){
        boolean success = false ;

        info.save();

        success = true ;

        return success ;
    }

    public boolean addInfos(List<AudioInfo> infos){

        boolean success = false ;

        ActiveAndroid.beginTransaction();
        try {
            for (AudioInfo info : infos) {
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
    public void deteleInfo(AudioInfo info){
        info.delete();
    }


    public void deteleInfos(List<AudioInfo> infos){
        ActiveAndroid.beginTransaction();
        try{
            for (AudioInfo info: infos){
                deteleInfo(info);
            }
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }
    }

    //edit

    //query
    public AudioInfo getInfo (int _id){
        return new Select().from(AudioInfo.class).where("_id = ?", _id).executeSingle() ;
    }

    public AudioInfo getInfo (String name){
        return new Select().from(AudioInfo.class).where("Name = ?", name).executeSingle() ;
    }

    public List<AudioInfo> getInfos (int userId){
        return new Select().from(AudioInfo.class).where("UserId = ?",userId).execute() ;
    }

    public List<AudioInfo> getAllInfosDesc(){
        List<AudioInfo> infos= new Select().all().from(AudioInfo.class).orderBy("TimeStamp DESC").execute() ;

        if (infos == null){
            Log.i(TAG,"getAllInfos : is null" ) ;
        }else {
            Log.i(TAG,"getAllInfos :"  + infos.toString()) ;

        }

        for (AudioInfo fileInfos : infos){
            Log.i(TAG,"fileinfo :" + fileInfos.toString()) ;
        }
        return infos;
    }

    public List<AudioInfo> getAllInfos(){
        List<AudioInfo> infos= new Select().all().from(AudioInfo.class).execute() ;

        if (infos == null){
            Log.i(TAG,"getAllInfos : is null" ) ;
        }else {
            Log.i(TAG,"getAllInfos :"  + infos.toString()) ;

        }

        for (AudioInfo fileInfos : infos){
            Log.i(TAG,"fileinfo :" + fileInfos.toString()) ;
        }
        return infos;
    }

    public AudioInfo getRandom() {
        return new Select().from(AudioInfo.class).orderBy("RANDOM()").executeSingle();
    }

}
