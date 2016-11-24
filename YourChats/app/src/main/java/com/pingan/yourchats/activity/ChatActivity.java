package com.pingan.yourchats.activity;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.pingan.yourchats.R;
import com.pingan.yourchats.adapter.EmotionVpAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liupeng on 2016/11/22.
 */

public class ChatActivity extends BaseActivity {


    @BindView(R.id.chat_lv)
    ListView     chatLv;
    @BindView(R.id.chat_edit)
    EditText     chatEdit;
    @BindView(R.id.send_btn)
    Button       sendBtn;
    @BindView(R.id.emotion_vp)
    ViewPager    emotionVp;
    @BindView(R.id.emo_tab_icon_layout)
    LinearLayout emoTabIconLayout;
    @BindView(R.id.emotion_indictor)
    LinearLayout emotionIndictor;
    @BindView(R.id.emotion_btn)
    ImageView    emotionBtn;
    private int lastPosition;


    @Override
    public int getViewLayout() {
        return R.layout.chat_activity;
    }

    @Override
    protected void loadData() {

    }

    private void changeVpIndicator(int position) {
        ImageView imageView = (ImageView) emotionIndictor.getChildAt(lastPosition);
        imageView.setImageResource(R.drawable.shape_vp_indicator);

        ImageView childAt = (ImageView) emotionIndictor.getChildAt(position);
        childAt.setImageResource(R.drawable.shape_vp_indicator_select);
        lastPosition = position;
    }

    private void addEmotionIndictor(int totalIndictor) {
        emotionIndictor.setGravity(Gravity.CENTER);
        for (int i = 0; i < totalIndictor; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(50, 4, 0, 4);
            if (i == 0) {
                imageView.setImageResource(R.drawable.shape_vp_indicator_select);
            } else {
                imageView.setImageResource(R.drawable.shape_vp_indicator);
                imageView.setLayoutParams(layoutParams);
            }
            emotionIndictor.addView(imageView);
        }

    }



    @OnClick({R.id.emotion_btn,R.id.send_btn})
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.emotion_btn:
                emotionVp.setVisibility(View.VISIBLE);
                emotionIndictor.setVisibility(View.VISIBLE);
                emoTabIconLayout.setVisibility(View.VISIBLE);
                emotionVp.setAdapter(new EmotionVpAdapter());
                if (emotionIndictor.getChildCount() == 0) {
                    addEmotionIndictor(5);
                }else{
                    changeVpIndicator(0);
                }
                lastPosition = 0;
                emotionVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }
                    @Override
                    public void onPageSelected(int position) {
                        changeVpIndicator(position);
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
                break;
            case R.id.send_btn:

                break;
            default:
        }
    }
}
