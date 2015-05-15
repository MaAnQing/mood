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
import com.hm.madroid.mood.adapter.RecordMsgAdapter;
import com.hm.madroid.mood.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;


public class RecordFragment extends Fragment implements View.OnClickListener{

    private AudioManager mAudioManager ;
    private DialogManager mDialogManager ;

    private ListView mListView ;
    private RecordMsgAdapter mAdapter ;
    private List<ChatMessage> mData ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAudioManager = AudioManager.getInstance() ;
        mDialogManager = new DialogManager(getActivity()) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        initView(view) ;
        return view;
    }

    private void initView(View view){
        mListView = (ListView)view.findViewById(R.id.List_record) ;

        mAdapter = new RecordMsgAdapter(getActivity(),getData()) ;
        mListView.setAdapter(mAdapter);
    }

    private List<ChatMessage> getData(){
        mData = new ArrayList<>() ;
        for (int i = 1; i< 16;i++){
            ChatMessage message = new ChatMessage() ;
            message.setMessageType(i%2);
            message.setMsg("message:" + i);
            message.setDuration(i * 10 + "''");
            mData.add(message) ;
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
