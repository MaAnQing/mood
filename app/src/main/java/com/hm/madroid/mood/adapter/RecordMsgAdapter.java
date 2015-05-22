package com.hm.madroid.mood.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hm.madroid.mood.AudioManager;
import com.hm.madroid.mood.Constant;
import com.hm.madroid.mood.R;
import com.hm.madroid.mood.Utils;
import com.hm.madroid.mood.model.ChatMessage;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by madroid on 15-5-11.
 */
public class RecordMsgAdapter extends BaseAdapter {


    private List<ChatMessage> mDataSet ;
    private Context mContext ;
    private LayoutInflater mLayoutInflater ;
    private static final String TAG = "RecordMsgAdapter" ;


    public RecordMsgAdapter(Context context,List<ChatMessage> data){

        mContext = context ;
        mDataSet = data ;
        mLayoutInflater = LayoutInflater.from(context) ;

    }

    @Override
    public int getCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    @Override
    public ChatMessage getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataSet.get(position).getMessageType();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null ;
        if (viewHolder == null){
            viewHolder = new ViewHolder() ;
            if (getItemViewType(position) == ChatMessage.MESSAGE_FROM){
                convertView = mLayoutInflater.inflate(R.layout.item_from_msg,null) ;
                viewHolder.initView(convertView,mDataSet.get(position));
            }else if (getItemViewType(position) == ChatMessage.MESSAGE_TO){
                convertView = mLayoutInflater.inflate(R.layout.item_to_msg,null) ;
                viewHolder.initView(convertView,mDataSet.get(position));
            }
            Log.i(TAG,"type:" + getItemViewType(position) ) ;
            convertView.setTag(viewHolder);
            Log.i(TAG,"tag:" + viewHolder) ;
        }else {

            viewHolder = (ViewHolder)convertView.getTag() ;
        }

        viewHolder.bindData(mDataSet.get(position));

        return convertView;
    }

    class ViewHolder{

        private TextView time ;
        private ImageView icon ;
        private TextView message ;
        private ImageView audio ;
        private TextView length ;
        private TextView duration ;
        private View  msgView ;
        private ChatMessage  mChatMessage ;



        public void initView(View v, ChatMessage msg){
            mChatMessage = msg ;
            time = (TextView)v.findViewById(R.id.msg_time) ;
            icon = (ImageView)v.findViewById(R.id.msg_icon) ;
            msgView = v.findViewById(R.id.msg_view) ;
            if (msg.getMessageType() == ChatMessage.MESSAGE_FROM){

                message = (TextView)v.findViewById(R.id.msg_text) ;
            }else if (msg.getMessageType() == ChatMessage.MESSAGE_TO){
                audio = (ImageView)v.findViewById(R.id.msg_audio) ;
                msgView.setOnClickListener(new AudioClickedListener());
                length = (TextView)v.findViewById(R.id.msg_length) ;
                duration = (TextView)v.findViewById(R.id.msg_duration) ;
            }

        }

        public void bindData(ChatMessage msg){
            if (msg.getTime() != null && !msg.getTime().equals("")){
                time.setVisibility(View.VISIBLE);
                time.setText(msg.getTime());
            } else {
                time.setVisibility(View.GONE);
            }

            if (msg.getMessageType() == ChatMessage.MESSAGE_FROM){
                message.setText(msg.getMsg());
            }else if (msg.getMessageType() == ChatMessage.MESSAGE_TO){
                duration.setText(msg.getDuration());
            }
        }

        class AudioClickedListener implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                audio.setBackgroundResource(R.drawable.play_anim);

                AnimationDrawable anim = (AnimationDrawable)audio.getBackground() ;
                if (anim.isRunning()) {
                    anim.stop();
                    audio.clearAnimation();
                    audio.setImageResource(R.drawable.audio);
                    //AudioManager.getInstance().playAudio(mChatMessage.getPath());
                    AudioManager.getInstance().stopAudio();
                    Log.i(TAG,"play audio") ;
                } else {
                    AudioManager.getInstance().playAudio(mChatMessage.getPath());
                    //AudioManager.getInstance().playAudio(Utils.getSDCardPath() + Constant.FILE_PATH + "audio.mp3");
                    audio.setImageResource(R.drawable.anim_1);
                    anim.start();
                }
            }
        }

    }
}
