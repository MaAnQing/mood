package com.hm.madroid.mood.database;

/**
 * Created by madroid on 15-5-7.
 */
public class AudioInfo {


    public String name;

    public String duration;

    public String date;

    public int mood;

    public int userId;

    public String address;

    public long timeStamp;

    public AudioInfo(){
        super();
    }

    public AudioInfo(String name,String duration,String date,int mood,int id,String address,long time){
        this.name = name ;
        this.duration = duration ;
        this.date = date ;
        this.mood = mood ;
        this.userId = id ;
        this.address = address ;
        this.timeStamp = time ;

    }


    @Override
    public String toString() {

        return "name: "+ name+ "; duration: "+ duration + "; date: " + date + "; mood: " +mood +
                "; userId: " + userId + "; address:" + address + "; timestamp: " + timeStamp;
    }
}
