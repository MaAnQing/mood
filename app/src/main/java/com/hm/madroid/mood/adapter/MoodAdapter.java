package com.hm.madroid.mood.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hm.madroid.mood.R;
import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.viewholder.MoodViewHolder;

import java.util.List;

/**
 * Created by madroid on 15-4-23.
 */
public class MoodAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    private List<AudioInfo> mDataSet ;

    private MoodViewHolder.onItemClickedListener mListener ;
    private MoodViewHolder.onItemLongClickedListener mLongListener ;

    public MoodAdapter(List<AudioInfo> data){
        mDataSet = data ;

    }

    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_show_item,parent,false) ;
        MoodViewHolder mHolder = new MoodViewHolder(item ) ;
        mHolder.setClickedListener(mListener);
        mHolder.setLongClickedListener(mLongListener);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {
        holder.setValue(mDataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    public void setItemClickedListener(MoodViewHolder.onItemClickedListener listener){
        mListener = listener ;
    }

    public void SetItemLongClickedListener(MoodViewHolder.onItemLongClickedListener listener){
        mLongListener = listener ;
    }

}
