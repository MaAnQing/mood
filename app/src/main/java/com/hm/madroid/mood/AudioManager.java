package com.hm.madroid.mood;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by madroid on 15-5-9.
 */
public class AudioManager {

    private static final String TAG = "AudioManager" ;
    private static AudioManager mManager ;
    private MediaRecorder mMediaRecorder ;
    private AudioManagerStateListener mListener ;
    private String mDir ;
    private String mCurrentFilePth ;
    private boolean isPrepared ;

    private AudioInfo info ;
    long start = 0 ;
    long end = 0 ;

    private int mAudioSource ;
    private int mOutputFormat ;
    private int mAudioEncoder ;
    private int mAudioChannel ;



    private AudioManager(){

        mDir = Utils.getSDCardPath() + Constant.PATH ;
    }

    public static AudioManager getInstance(){

        if (mManager == null){
            synchronized (AudioManager.class){
                if (mManager == null){
                    mManager = new AudioManager() ;
                }
            }
        }
        return mManager ;
    }

    public String getCurrentFilePath() {
        return mCurrentFilePth;
    }

    public interface AudioManagerStateListener{

        public void wellPrepared() ;
    }

    public void setStateListener(AudioManagerStateListener listener){
        mListener = listener ;
    }

    public void prepareAudio(){

        try {

            info = new AudioInfo() ;

            isPrepared = false ;

            File dir = new File(mDir) ;
            if (!dir.exists()){
                dir.mkdir() ;
            }

            String fileName = generateFilename() ;
            File file = new File(dir,fileName) ;
            mCurrentFilePth = file.getAbsolutePath() ;
            info.path = mCurrentFilePth ;
            Log.i(TAG,"file path" + mCurrentFilePth) ;

            mMediaRecorder = new MediaRecorder() ;
            mMediaRecorder.setOutputFile(mCurrentFilePth);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            mMediaRecorder.setOutputFormat(MediaRecorder.AudioEncoder.AMR_NB);
            //设置编码为arm
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            mMediaRecorder.setAudioChannels(1);

            mMediaRecorder.prepare();
            mMediaRecorder.start();

            info.timeStamp = Calendar.getInstance().getTimeInMillis() ;
            isPrepared = true ;


            if (mListener != null)
                mListener.wellPrepared();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void startRecord(){
        prepareAudio();
    }

    public void stopRecord(){
        release();
        saveToDB();
    }

    private String generateFilename(){
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh:mm") ;
        String filename = format.format(new Date()) ;
        info.name = filename ;
        String suffix = ".arm" ;
        return  filename + suffix;
    }

    public int getAudioLevel(int maxLevel){

        try {
            if (isPrepared){
                //mMediaRecorder.getMaxAmplitude() 1-32767
                return  maxLevel * mMediaRecorder.getMaxAmplitude() / 32768 + 1 ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return 1 ;
    }

    public void cancel(){
        release();

        if (mCurrentFilePth != null){
            File file = new File(mCurrentFilePth) ;
            file.delete() ;
            mCurrentFilePth = null ;
        }

    }

    public void release(){
        end = Calendar.getInstance().getTimeInMillis() ;
        if (mMediaRecorder != null){
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null ;
        }
    }

    private void saveToDB(){
        info.duration = end - info.timeStamp ;
        info.date = Utils.getDate(info.timeStamp) ;
        info.address = Utils.getAddress() ;
        info.mood = Utils.getMood() ;
        AudioInfoManager.getInstance().addInfo(info) ;
    }

    public void playAudio(String path){
        MediaPlayer player = new MediaPlayer();

        try {
            player.setDataSource(path);

            player.prepare();
        }catch (IOException e){
            Log.e(TAG,"not find audio file") ;
            e.printStackTrace();
        }


        player.start();
    }


}
