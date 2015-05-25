package com.hm.madroid.mood;

import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import com.hm.madroid.mood.database.AudioInfo;
import com.hm.madroid.mood.database.AudioInfoManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.greenrobot.event.EventBus;

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
    private MediaPlayer mPlayer ;


    private AudioInfo info ;
    long start = 0 ;
    long end = 0 ;

    private int mOutputFormat ;
    private int mAudioEncoder ;
    private int mAudioChannel ;
    private int mSampleRateInHz ;
    private int mBufferSizeInBytes ;
    private int mAudioFormat ;



    private AudioManager(){

        mDir = Utils.getSDCardPath() + Constant.FILE_PATH;
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
            Log.i(TAG, "file path" + mCurrentFilePth) ;

            mMediaRecorder = new MediaRecorder() ;
            mMediaRecorder.setOutputFile(mCurrentFilePth);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            mOutputFormat = MediaRecorder.AudioEncoder.AMR_NB ;
            mMediaRecorder.setOutputFormat(mOutputFormat);

            //设置编码为arm
            mAudioEncoder = MediaRecorder.AudioEncoder.AMR_NB ;
            mMediaRecorder.setAudioEncoder(mAudioEncoder);

            //设置单声道，立体声
            mAudioChannel = getAudioChannel() ;
            mMediaRecorder.setAudioChannels(mAudioChannel);

            //设置采样率
            mSampleRateInHz = Keeper.readRecordSampleRate() ;
            mMediaRecorder.setAudioSamplingRate(mSampleRateInHz);

            mBufferSizeInBytes = AudioRecord.getMinBufferSize(mSampleRateInHz,mAudioChannel,mOutputFormat) ;
           // mMediaRecorder = new MediaRec

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


    private int getAudioChannel(){
        //1 (单通道) or 2 双通道
        return Keeper.readRecordChannel() ? 2 : 1 ;
    }

    public void startRecord(){
        prepareAudio();
    }

    public void stopRecord(){
        release();
//        TransThread thread = new TransThread() ;
//        thread.start();
        saveToDB();
    }

    private String generateFilename(){
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh:mm") ;
        String filename = format.format(new Date()) ;
        info.name = filename ;
        String suffix = ".amr" ;
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
        EventBus.getDefault().post(info);

    }

    public void playAudio(String path){
        mPlayer = new MediaPlayer() ;

        try {
            mPlayer.setDataSource(path);

            mPlayer.prepare();
        }catch (IOException e){
            Log.e(TAG,"not find audio file") ;
            e.printStackTrace();
        }

        mPlayer.start();
    }

    public int getDuration(){
        return mPlayer.getDuration() ;
    }

    public boolean isPlaying(){
        return mPlayer != null && mPlayer.isPlaying() ;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener){
        mPlayer.setOnCompletionListener(listener);
    }

    public void stopAudio() {
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    class TransThread extends Thread {
        @Override
        public void run() {
            super.run();
            Log.i(TAG, "1") ;
            copyWaveFile(mCurrentFilePth, Utils.transFormatStr(mCurrentFilePth, ".wav"));
            Log.i(TAG, "2") ;
            saveToDB();
        }
    }

    /**
     * 这里将数据写入文件，但是并不能播放，因为AudioRecord获得的音频是原始的裸音频，
     * 如果需要播放就必须加入一些格式或者编码的头信息。但是这样的好处就是你可以对音频的 裸数据进行处理，比如你要做一个爱说话的TOM
     * 猫在这里就进行音频的处理，然后重新封装 所以说这样得到的音频比较容易做一些音频的处理。
     */
    public void copyWaveFile(String inFilename, String outFilename) {
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen + 36;
        long longSampleRate = mSampleRateInHz;
        int channels = 2;
        long byteRate = 16 * mSampleRateInHz * channels / 8;
        byte[] data = new byte[mBufferSizeInBytes];
        try {
            in = new FileInputStream(inFilename);
            File file = new File(outFilename);
            file.createNewFile();
            out = new FileOutputStream(outFilename);
            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;
            WriteWaveFileHeader(out, totalAudioLen, totalDataLen,
                    longSampleRate, channels, byteRate);
            while (in.read(data) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
            //生成wav文件后删除raw文件
            new File(inFilename).delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里提供一个头信息。插入这些信息就可以得到可以播放的文件。
     * 为我为啥插入这44个字节，这个还真没深入研究，不过你随便打开一个wav
     * 音频的文件，可以发现前面的头文件可以说基本一样哦。每种格式的文件都有
     * 自己特有的头文件。
     */
    public void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen,
                                    long totalDataLen, long longSampleRate, int channels, long byteRate)
            throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (2 * 16 / 8); // block align
        header[33] = 0;
        header[34] = 16; // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }

}
