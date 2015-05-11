package com.hm.madroid.mood.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by madroid on 15-5-11.
 */
public class RecordMsgAdapter extends BaseAdapter {


    private List<String> mDataSet ;


    public RecordMsgAdapter(){

    }

    @Override
    public int getCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }

    class ViewHolder{

    }
}
