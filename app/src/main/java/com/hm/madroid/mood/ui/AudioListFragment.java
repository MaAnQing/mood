package com.hm.madroid.mood.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hm.madroid.mood.R;
import com.hm.madroid.mood.adapter.MoodAdapter;
import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;
import com.hm.madroid.mood.viewholder.MoodViewHolder;

import java.io.File;
import java.util.List;

import de.greenrobot.event.EventBus;

public class AudioListFragment extends Fragment implements MoodViewHolder.onItemClickedListener,MoodViewHolder.onItemLongClickedListener{

    private static final String TAG = "AudioListFragment";

    private RecyclerView mRecycler ;
    private MoodAdapter mAdapter ;
    private List<AudioInfo> mDataSet ;
    private Context mContext ;


    public AudioListFragment() {
        // Required empty public constructor
        mContext = getActivity() ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_list, container, false);
        initView(view) ;
        Log.i(TAG, "onCreateView :") ;

        return view ;
    }

    private void initView(View view){
        mRecycler = (RecyclerView)view.findViewById(R.id.myRecycler) ;
        mAdapter = new MoodAdapter(getDataSet()) ;
        mAdapter.setItemClickedListener(this);
        mAdapter.SetItemLongClickedListener(this);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setItemAnimator(new DefaultItemAnimator());


        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext) ;
        mRecycler.setLayoutManager(manager);


    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private List<AudioInfo> getDataSet(){
        AudioInfoManager manager = AudioInfoManager.getInstance() ;
        mDataSet = manager.getAllInfos();
        Log.i(TAG,"dataset :" + mDataSet) ;
        return mDataSet ;
    }

    //录音结束后，刷新列表
    public void onEvent(AudioInfo info){
        //TODO:
    }

    //recycler item click
    @Override
    public void onClick(View view, int position) {
        Log.i("madroid", "position: " + position) ;
//        mDataSet.remove(position) ;
//        mAdapter.notifyItemRemoved(position);
//        Uri uri = Uri.fromFile(new File(mDataSet.get(position).path ));
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(uri,"audio/x-wav");
//        getActivity().startActivity(intent);

    }

    @Override
    public void onLongClick(View view, int position) {
        Toast.makeText(getActivity(),"long position:" + position,Toast.LENGTH_SHORT).show();
        Log.i("madroid", "long position: " + position) ;
    }

}
