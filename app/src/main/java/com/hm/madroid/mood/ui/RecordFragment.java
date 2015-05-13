package com.hm.madroid.mood.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hm.madroid.mood.AudioManager;
import com.hm.madroid.mood.Constant;
import com.hm.madroid.mood.R;
import com.hm.madroid.mood.Utils;


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
        recordBtn.setOnTouchListener(new MyOnTouchListener());
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

    public class MyOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    //record.setImageResource(R.drawable.ic_recording);
                    //Log.i(TAG, "MyOnTouchListener");
                    mAudioManager.startRecord();
                    mDialogManager.showRecording();
                    new TimeThread().start();
                    break;
                case MotionEvent.ACTION_UP:
                    mDialogManager.dimiss();
                    mAudioManager.stopRecord();
//                    record.setImageResource(R.drawable.ic_record);
//                    upload();
                    break;
            }
            return true;

        }

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
