package com.hm.madroid.mood.event;

/**
 * Created by madroid on 15-5-11.
 */
public class UpdateTitle {

    private int position ;

    public UpdateTitle(int id){
        this.position = id ;
    }

    public int getPosition() {
        return position;
    }
}
