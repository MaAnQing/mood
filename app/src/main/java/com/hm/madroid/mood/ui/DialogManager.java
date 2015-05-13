package com.hm.madroid.mood.ui;

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

    private Dialog mDialog ;

    private ImageView mLevel ;
    private ImageView mCancel ;
    private ImageView mIcon ;

    private TextView mLabel ;

    private Context mContext ;

    public DialogManager(Context context){
        mContext = context ;
    }

    public void showRecording(){
        mDialog = new Dialog(mContext, R.style.dialog) ;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_record,null) ;
        mDialog.setContentView(view);

        mCancel = (ImageView)mDialog.findViewById(R.id.record_cancel) ;
        mIcon = (ImageView)mDialog.findViewById(R.id.record_icon) ;
        mLevel = (ImageView)mDialog.findViewById(R.id.record_level) ;
        mLabel = (TextView)mDialog.findViewById(R.id.record_label) ;

        mCancel.setVisibility(View.GONE);
        mIcon.setVisibility(View.VISIBLE);
        mLevel.setVisibility(View.VISIBLE);

        mDialog.show() ;

    }

    public void recording(){
        if (mDialog != null && mDialog.isShowing()){
            mCancel.setVisibility(View.GONE);
            mIcon.setVisibility(View.VISIBLE);
            mLevel.setVisibility(View.VISIBLE);
            mLabel.setText("松开手指，取消发送");
        }
    }

    public void wantToCancel(){

    }

    public void tooShort(){

    }

    public void dimiss(){
        mDialog.dismiss();
    }

    public void updateLevel(int level){
        if (mDialog != null && mDialog.isShowing()){
            mCancel.setVisibility(View.GONE);
            mIcon.setVisibility(View.VISIBLE);
            mLevel.setVisibility(View.VISIBLE);

            mLevel.setImageResource(getPic(level));
            mLabel.setText("松开手指，取消发送");
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
