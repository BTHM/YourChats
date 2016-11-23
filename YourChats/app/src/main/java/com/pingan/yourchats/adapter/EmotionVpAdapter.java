package com.pingan.yourchats.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Author：liupeng on 2016/11/23 18:30
 * Address：liupeng264@pingan.com.cn
 */
public class EmotionVpAdapter extends PagerAdapter {

    private final Context mContext;

    public EmotionVpAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GridView gridView = new GridView(mContext);
        gridView.setNumColumns(7);
        //gridView.setAdapter(new );
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
