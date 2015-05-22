package com.hm.madroid.mood.model;


/**
 * Created by madroid on 15-5-14.
 */
public class ChatMessage {

    public static final int MESSAGE_FROM = 0 ;//从0开始，否则报数组越界
    public static final int MESSAGE_TO = 1 ;

    private int messageType ;
    private int messageConut ;
    private String msg ;
    private String duration ;
    private int length ;
    private String time ;
    private String path ;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {

        return length;
    }

    public String getDuration() {
        return duration;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getMessageConut() {
        return messageConut;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public void setMessageConut(int messageConut) {
        this.messageConut = messageConut;
    }
}
