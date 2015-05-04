package com.hm.madroid.mood.database;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by madroid on 15-4-27.
 */

public class FileInfoManager {

    private String TAG = "FileInfoManager" ;

    private static FileInfoManager manager ;

    public FileInfoManager(){

    }

    public static FileInfoManager getInstance(){
        if (manager ==null){
            manager = new FileInfoManager() ;
        }
        return manager ;
    }

    //add
    public boolean addInfo(FileInfo info){
        boolean success = false ;

        info.save();

        success = true ;

        return success ;
    }

    public boolean addInfos(List<FileInfo> infos){

        boolean success = false ;

        ActiveAndroid.beginTransaction();
        try {
            for (FileInfo info : infos) {
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
    public void deteleInfo(FileInfo info){
        info.delete();
    }


    public void deteleInfos(List<FileInfo> infos){
        ActiveAndroid.beginTransaction();
        try{
            for (FileInfo info: infos){
                deteleInfo(info);
            }
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }
    }

    //edit

    //query
//    public int getAllInfosCount(){
//        return new Select().from(UserInfo.class).where().c
//    }

    public FileInfo getInfo (int id){
        return new Select().from(UserInfo.class).where("UserId = ?",id).executeSingle() ;
    }

    public List<FileInfo> getAllInfos(){
        return new Select().from(UserInfo.class).where("").execute();
    }

}
