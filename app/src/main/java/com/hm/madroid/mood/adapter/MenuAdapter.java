package com.hm.madroid.mood.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.madroid.mood.R;
import com.hm.madroid.mood.model.MenuModel;

import java.util.List;

/**
 * Created by madroid on 15-5-25.
 */
public class MenuAdapter extends BaseAdapter {

    private Context mContext ;
    private List<MenuModel> mData ;
    private LayoutInflater mLayoutInflater ;
    private static final String TAG = "MenuAdapter" ;

    public MenuAdapter(Context context, List<MenuModel> data){
        mContext = context ;
        mData = data ;
        mLayoutInflater = LayoutInflater.from(mContext) ;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public MenuModel getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            Log.i(TAG,"init viewholder") ;
            viewHolder = new ViewHolder() ;
            convertView = mLayoutInflater.inflate(R.layout.drawer_list_item,null) ;
            viewHolder.initView(convertView) ;
            convertView.setTag(viewHolder);

        } else {
            Log.i(TAG,"get viewholder") ;

            viewHolder = (ViewHolder)convertView.getTag() ;
        }

        viewHolder.bindData(mData.get(position));

        return convertView;
    }

    class ViewHolder {
        private ImageView icon ;
        private TextView title ;

        private void initView(View view) {
            icon = (ImageView)view.findViewById(R.id.setting_ic) ;
            title = (TextView)view.findViewById(R.id.setting_title) ;
        }

        private void bindData(MenuModel model) {
            if (model != null) {
                icon.setImageResource(model.getResId());
                title.setText(model.getTitle());
            }
        }

    }
}
