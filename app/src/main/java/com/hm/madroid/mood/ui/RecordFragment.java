package com.hm.madroid.mood.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hm.madroid.mood.AudioManager;
import com.hm.madroid.mood.DialogManager;
import com.hm.madroid.mood.R;


public class RecordFragment extends Fragment implements View.OnClickListener{

    private Button recordBtn ;
    private AudioManager mAudioManager ;
    private String mFilePath ;
    private DialogManager mDialogManager ;


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
        recordBtn = (Button)view.findViewById(R.id.btn_record) ;
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



    class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                sleep(100);
                mDialogManager.updateLevel(mAudioManager.getAudioLevel(7));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
