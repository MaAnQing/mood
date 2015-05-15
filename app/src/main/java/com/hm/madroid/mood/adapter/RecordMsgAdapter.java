package com.hm.madroid.mood.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.madroid.mood.R;
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
                viewHolder.initView(convertView,ChatMessage.MESSAGE_FROM);
            }else if (getItemViewType(position) == ChatMessage.MESSAGE_TO){
                convertView = mLayoutInflater.inflate(R.layout.item_to_msg,null) ;
                viewHolder.initView(convertView,ChatMessage.MESSAGE_TO);
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



        public void initView(View v, int type){
            time = (TextView)v.findViewById(R.id.msg_time) ;
            icon = (ImageView)v.findViewById(R.id.msg_icon) ;
            if (type == ChatMessage.MESSAGE_FROM){
                message = (TextView)v.findViewById(R.id.msg_text) ;
            }else if (type == ChatMessage.MESSAGE_TO){
                audio = (ImageView)v.findViewById(R.id.msg_audio) ;
                length = (TextView)v.findViewById(R.id.msg_length) ;
                duration = (TextView)v.findViewById(R.id.msg_duration) ;
            }

        }

        public void bindData(ChatMessage msg){
            if (msg.getMessageType() == ChatMessage.MESSAGE_FROM){
                message.setText(msg.getMsg());
            }else if (msg.getMessageType() == ChatMessage.MESSAGE_TO){
                duration.setText(msg.getDuration());
            }
        }

    }
}
