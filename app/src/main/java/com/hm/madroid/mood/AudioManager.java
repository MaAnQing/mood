package com.hm.madroid.mood;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
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

    private AudioManager(String path){
        mDir = path ;
    }

    public static AudioManager getInstance(String path){

        if (mManager == null){
            synchronized (AudioManager.class){
                if (mManager == null){
                    mManager = new AudioManager(path) ;
                }
            }
        }
        return mManager ;
    }

    public interface AudioManagerStateListener{

        public void wellPrepared() ;
    }

    public void setStateListener(AudioManagerStateListener listener){
        mListener = listener ;
    }

    public void prepareAudio(){

        try {

            isPrepared = false ;

            File dir = new File(mDir) ;
            if (!dir.exists()){
                dir.mkdir() ;
                Log.i(TAG, "is mkdir file : " + dir.mkdir()) ;
            }

            String fileName = generateFilename() ;
            File file = new File(dir,fileName) ;
            mCurrentFilePth = file.getAbsolutePath() ;
            Log.i(TAG,"file path" + mCurrentFilePth) ;

            mMediaRecorder = new MediaRecorder() ;
            mMediaRecorder.setOutputFile(mCurrentFilePth);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            mMediaRecorder.setOutputFormat(MediaRecorder.AudioEncoder.AMR_NB);
            //设置编码为arm
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            mMediaRecorder.prepare();
            mMediaRecorder.start();

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
    }

    private String generateFilename(){
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh：mm") ;
        String filename = format.format(new Date()) ;
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

    public void cancle(){
        release();

        if (mCurrentFilePth != null){
            File file = new File(mCurrentFilePth) ;
            file.delete() ;
            mCurrentFilePth = null ;
        }

    }

    public void release(){
        if (mMediaRecorder != null){
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null ;
        }
    }
}
