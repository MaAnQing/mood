package com.hm.madroid.mood;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.madroid.mood.R;

/**
 * Created by madroid on 15-5-11.
 */
public class DialogManager {

    private Dialog mDialog;
    private static final String TAG = "DialogManager";

    private ImageView mIcon;
    private ImageView mVideo;
    private TextView mLable;

    private Context mContext ;

    public DialogManager(Context context){
        mContext = context ;
    }

    public void showRecordingDialog(){
        Log.i(TAG,"showRecordingDialog") ;
        mDialog = new Dialog(mContext, R.style.dialog);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_record,null);
        mDialog.setContentView(view);
        mIcon = (ImageView) mDialog.findViewById(R.id.image_a);
        mVideo = (ImageView) mDialog.findViewById(R.id.image_b);
        mLable = (TextView) mDialog.findViewById(R.id.tv_dialogcontent);
        mDialog.show();
    }


    public void recoding(){
        Log.i(TAG,"recoding") ;
        if (mDialog != null&& mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVideo.setVisibility(View.VISIBLE);
            mLable.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.dialog_recorder);
            mLable.setText(mContext.getString(R.string.dialog_msg_cancel));

        }

    }


    public void wantToCancel(){
        Log.i(TAG,"wantToCancel") ;
        if (mDialog != null&& mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVideo.setVisibility(View.GONE);
            mLable.setVisibility(View.VISIBLE);
            mIcon.setImageResource(R.drawable.cancel);
            mLable.setText(mContext.getString(R.string.dialog_msg_want_cancel));
        }
    }

    public void tooShort(){
        Log.i(TAG,"tooShort") ;
        if (mDialog != null&& mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mIcon.setImageResource(R.drawable.voice_to_short);
            mVideo.setVisibility(View.GONE);
            mLable.setVisibility(View.VISIBLE);
            mLable.setText(mContext.getString(R.string.dialog_msg_short));
        }

    }

    public void dismissDialog(){
        Log.i(TAG,"dismissDialog") ;
        if (mDialog != null&& mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog =null;
        }

    }


    public void updateLevel(int level){
        Log.i(TAG,"updateLevel") ;
        if (mDialog != null && mDialog.isShowing()){
            mIcon.setVisibility(View.VISIBLE);
            //mVideo.setVisibility(View.VISIBLE);
            mVideo.setImageResource(getPic(level));
            mLable.setText(mContext.getString(R.string.dialog_msg_cancel));
        }
    }

    private int getPic(int level){
        int id ;
        switch (level){
            case 1 :
                id = R.drawable.level_1 ;
                break;
            case 2:
                id = R.drawable.level_2 ;
                break;
            case 3 :
                id = R.drawable.level_3 ;
                break;
            case 4:
                id = R.drawable.level_4 ;
                break;
            case 5 :
                id = R.drawable.level_5 ;
                break;
            case 6:
                id = R.drawable.level_6 ;
                break;
            case 7:
                id = R.drawable.level_7 ;
                break;
            default:
                id = R.drawable.level_1 ;
                break;

        }
        return id ;
    }
}
