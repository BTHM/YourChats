package com.pingan.yourchats.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Author：liupeng on 2016/11/23 18:34
 * Address：liupeng264@pingan.com.cn
 */
public class EmotionGvAdapter extends BaseAdapter {

    private  Context mContext;
    private  List mDatas;

    public EmotionGvAdapter(Context context, List datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
