package com.hm.madroid.mood.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by madroid on 15-4-27.
 */

@Table(name = "UserInfo",id = "_id")
public class UserInfo extends Model {

    @Column(name = "UserName")
    public String userName;

    @Column(name = "Password")
    public String password;

    @Column(name = "PhoneNumber")
    public String phoneNumber;

    @Column(name = "Email")
    public String email;

    @Column(name = "Gender")
    public int gender;

    @Column(name = "Birthday")
    public String birthday;

    @Column(name = "Address")
    public String address;

    @Column(name = "UserId")
    public int userId;

    // 确保每个model类中都有一个默认的构造方法
    public UserInfo(){

    }

    public UserInfo(String name,String pwd,String phone ,String email,int gender,String birthday,String address ,int id){
        this.userName = name ;
        this.password = pwd ;
        this.phoneNumber = phone ;
        this.email = email ;
        this.gender = gender ;
        this.birthday = birthday ;
        this.address = address ;
        this.userId = id ;

    }
}
