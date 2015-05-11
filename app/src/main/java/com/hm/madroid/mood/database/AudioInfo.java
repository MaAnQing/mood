package com.hm.madroid.mood.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by madroid on 15-5-7.
 */
@Table(name = "AudioInfos",id = "_id")
public class AudioInfo extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Duration")
    public long duration;

    @Column(name = "Date")
    public String date;

    @Column(name = "Mood")
    public int mood;

    @Column(name = "UserId")
    public int userId;

    @Column(name = "Address")
    public String address;

    @Column(name = "TimeStamp")
    public long timeStamp;

    @Column(name = "AudioPath")
    public String path;

    public AudioInfo(){
        super();
    }

    public AudioInfo(String name,long duration,String date,int mood,int id,String address,long time,String path){
        this.name = name ;
        this.duration = duration ;
        this.date = date ;
        this.mood = mood ;
        this.userId = id ;
        this.address = address ;
        this.timeStamp = time ;
        this.path = path ;

    }


    @Override
    public String toString() {

        return "name: "+ name+ "; duration: "+ duration + "; date: " + date + "; mood: " +mood +
                "; userId: " + userId + "; address:" + address + "; timestamp: " + timeStamp;
    }
}
