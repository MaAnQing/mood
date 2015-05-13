package com.hm.madroid.mood;

import android.app.Dialog;
import android.content.Context;
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

    private ImageView mIcon;
    private ImageView mVideo;
    private TextView mLable;

    private Context mContext ;

    public DialogManager(Context context){
        mContext = context ;
    }

    public void showRecordingDialog(){
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
        if (mDialog != null&& mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVideo.setVisibility(View.VISIBLE);
            mLable.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.dialog_recorder);
            mLable.setText("手指上滑，取消发送");

        }

    }


    public void wantToCancel(){
        if (mDialog != null&& mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVideo.setVisibility(View.GONE);
            mLable.setVisibility(View.VISIBLE);
            mIcon.setImageResource(R.drawable.cancel);
            mLable.setText("松开手指，取消发送");


        }
    }

    public void tooShort(){
        if (mDialog != null&& mDialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVideo.setVisibility(View.GONE);
            mLable.setVisibility(View.VISIBLE);

            mIcon.setImageResource(R.drawable.voice_to_short);
            mLable.setText("录音时间过短");
        }

    }

    public void dismissDialog(){
        if (mDialog != null&& mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog =null;
        }

    }


    public void updateLevel(int level){
        if (mDialog != null && mDialog.isShowing()){
            mIcon.setVisibility(View.VISIBLE);
            mVideo.setVisibility(View.VISIBLE);

            mVideo.setImageResource(getPic(level));
            mLable.setText("松开手指，取消发送");
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
