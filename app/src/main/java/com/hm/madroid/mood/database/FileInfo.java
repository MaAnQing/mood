package com.hm.madroid.mood.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by madroid on 15-4-27.
 */

@Table(name = "Fileinfos")
public class FileInfo extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Duration")
    public String duration;

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

    public FileInfo(){
        super();
    }

    public FileInfo(String name,String duration,String date,int mood,int id,String address,long time){
        this.name = name ;
        this.duration = duration ;
        this.date = date ;
        this.mood = mood ;
        this.userId = id ;
        this.address = address ;
        this.timeStamp = time ;

    }

}
