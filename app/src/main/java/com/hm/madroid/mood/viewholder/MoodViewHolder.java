package com.hm.madroid.mood.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.madroid.mood.Constant;
import com.hm.madroid.mood.R;
import com.hm.madroid.mood.database.AudioInfo;


/**
 * Created by madroid on 15-4-23.
 */
public class MoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    private TextView name ;
    private TextView date ;
    private TextView address ;
    private TextView duration ;
    private ImageView picId ;

    private onItemClickedListener mListener ;
    private onItemLongClickedListener mLongListener ;

    public MoodViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        initViews(itemView);

    }


    private void initViews(View itemView){
        name = (TextView)itemView.findViewById(R.id.tv_file_name) ;
        date = (TextView)itemView.findViewById(R.id.tv_file_date) ;
        address = (TextView)itemView.findViewById(R.id.tv_file_address) ;
        duration = (TextView)itemView.findViewById(R.id.tv_file_duration) ;
        picId = (ImageView)itemView.findViewById(R.id.image_pic) ;

    }

    public void setValue(AudioInfo value){
        name.setText(value.name);
        date.setText(value.date);
        duration.setText(value.duration);
        address.setText(value.address);
        picId.setImageResource(mood2Pic(value.mood));
    }

    private int mood2Pic(int mood){
        int resId ;
        switch (mood){
            case Constant.MOOD_ANGRY :
                resId = R.drawable.list_angry ;
                break;
            case Constant.MOOD_HAPPY:
                resId = R.drawable.list_happy ;
                break;
            case Constant.MOOD_NEUTRAL:
                resId = R.drawable.list_neutral ;
                break;
            case Constant.MOOD_SAD:
                resId = R.drawable.list_sad ;
                break;
            default:
                resId = R.drawable.list_neutral ;
        }
        return resId ;
    }

    public void setClickedListener(onItemClickedListener listener){
        mListener = listener ;
    }

    public void setLongClickedListener(onItemLongClickedListener listener){
        mLongListener = listener ;
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v,getPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        mLongListener.onLongClick(v,getPosition());
        return false;
    }


    public interface onItemClickedListener{
        public void onClick(View view, int position) ;
    }

    public interface onItemLongClickedListener{
        public void onLongClick(View view, int position) ;
    }

}
