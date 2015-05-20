package com.hm.madroid.mood.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.hm.madroid.mood.AudioManager;
import com.hm.madroid.mood.DialogManager;
import com.hm.madroid.mood.R;
import com.hm.madroid.mood.Utils;
import com.hm.madroid.mood.adapter.RecordMsgAdapter;
import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;
import com.hm.madroid.mood.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class RecordFragment extends Fragment implements View.OnClickListener{

    private AudioManager mAudioManager ;
    private DialogManager mDialogManager ;
    private AudioInfoManager mAudioInfoManager ;

    private ListView mListView ;
    private RecordMsgAdapter mAdapter ;
    private List<ChatMessage> mData ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAudioManager = AudioManager.getInstance() ;
        mDialogManager = new DialogManager(getActivity()) ;
        mAudioInfoManager = AudioInfoManager.getInstance() ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        initView(view) ;
        return view;
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

    public void onEvent(AudioInfo info){

        updateAudioList(info) ;

    }

    private void updateAudioList(AudioInfo info) {
        ChatMessage audio = new ChatMessage() ;
        audio.setMessageType(ChatMessage.MESSAGE_TO);
        audio.setTime(Utils.formatTime(info.timeStamp));
        audio.setDuration(info.duration /1000 + "'");
        ChatMessage mood = new ChatMessage() ;
        mood.setMessageType(ChatMessage.MESSAGE_FROM);
        mood.setMsg(Utils.getMoodStr(info.mood));
        mData.add(audio) ;
        mData.add(mood) ;
        mAdapter.notifyDataSetChanged();
        mListView.setSelection(mAdapter.getCount() - 1);
    }

    private void initView(View view){
        mListView = (ListView)view.findViewById(R.id.List_record) ;
        mAdapter = new RecordMsgAdapter(getActivity(),getData()) ;
        mListView.setAdapter(mAdapter);
        mListView.setSelection(mAdapter.getCount() - 1);
    }

    private List<ChatMessage> getData(){
        mData = new ArrayList<>() ;
        List<AudioInfo> audioInfos = mAudioInfoManager.getAllInfos() ;
        if (audioInfos != null) {
            for (AudioInfo list : audioInfos) {
                ChatMessage audio = new ChatMessage() ;
                audio.setMessageType(ChatMessage.MESSAGE_TO);
                audio.setTime(Utils.formatTime(list.timeStamp));
                audio.setDuration(list.duration /1000 + "'");
                ChatMessage mood = new ChatMessage() ;
                mood.setMessageType(ChatMessage.MESSAGE_FROM);
                mood.setMsg(Utils.getMoodStr(list.mood));
                mData.add(audio) ;
                mData.add(mood) ;
            }
        }
        return mData ;
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_record :
                startRecord() ;
                break;
        }
    }

    private void startRecord(){
        Toast.makeText(getActivity(),"click",Toast.LENGTH_SHORT).show();
        mAudioManager.startRecord();
    }

}
