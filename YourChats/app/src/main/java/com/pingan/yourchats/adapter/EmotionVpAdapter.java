package com.pingan.yourchats.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import widget.EmotionGridView;


/**
 * Author：liupeng on 2016/11/23 18:30
 * Address：liupeng264@pingan.com.cn
 */
public class EmotionVpAdapter extends PagerAdapter {

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
        EmotionGridView gridView = new EmotionGridView(container.getContext());
        container.addView(gridView);
        return gridView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
