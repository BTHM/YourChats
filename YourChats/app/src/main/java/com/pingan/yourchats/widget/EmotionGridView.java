package com.pingan.yourchats.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.pingan.yourchats.R;

/**
 * @author sanshu
 * @data 2016/11/23 22:55
 * @ToDo ${TODO}
 */

public class EmotionGridView extends GridView {
    public EmotionGridView(Context context) {
        this(context,null);
    }

    public EmotionGridView(Context context, AttributeSet attrs) {
        this(context,null,0);
    }

    public EmotionGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置列
        this.setNumColumns(7);
        //设置数值 间距
        float density = getResources().getDisplayMetrics().density;
        this.setVerticalSpacing((int) (10*density));
        this.setAdapter(new MyEmtionAdapter());
    }


    class MyEmtionAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 21;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.smiley_00);
            return imageView;
        }
    }
}
