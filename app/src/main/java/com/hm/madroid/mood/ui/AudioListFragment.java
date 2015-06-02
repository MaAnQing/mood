package com.hm.madroid.mood.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hm.madroid.mood.AudioManager;
import com.hm.madroid.mood.R;
import com.hm.madroid.mood.adapter.MoodAdapter;
import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;
import com.hm.madroid.mood.viewholder.MoodViewHolder;

import java.util.List;


public class AudioListFragment extends Fragment implements MoodViewHolder.onItemClickedListener,MoodViewHolder.onItemLongClickedListener{

    private static final String TAG = "AudioListFragment";

    private RecyclerView mRecycler ;
    private MoodAdapter mAdapter ;
    private List<AudioInfo> mDataSet ;
    private Context mContext ;
    private AudioInfoManager manager ;


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
    public void onResume() {
        super.onResume();
        mDataSet = getDataSet() ;
        mAdapter.setData(mDataSet);
        mAdapter.notifyDataSetChanged();
    }

    private List<AudioInfo> getDataSet(){
        manager = AudioInfoManager.getInstance() ;
        mDataSet = manager.getAllInfosDesc();
        Log.i(TAG, "dataset :" + mDataSet) ;
        return mDataSet ;
    }

    //recycler item click
    @Override
    public void onClick(View view, int position) {
//        TextView textView = (TextView)view.findViewById(R.id.tv_file_name) ;
//        String name = textView.getText().toString();
//        Log.i(TAG, "position: " + position + "; name :" + name) ;
//        AudioInfo info = manager.getInfo(name) ;
//        palyAudio(info) ;

        startActivity(new Intent(getActivity(),AudioDetailActivity.class));
    }

    private void palyAudio(AudioInfo info) {
        AudioManager audioManager = AudioManager.getInstance() ;
        Log.i(TAG,"path: " + info.path) ;
        audioManager.playAudio(info.path);
    }

    @Override
    public void onLongClick(View view, int position) {
        Toast.makeText(getActivity(),"long position:" + position,Toast.LENGTH_SHORT).show();
        Log.i("madroid", "long position: " + position) ;
    }

}
