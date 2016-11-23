package com.pingan.yourchats.activity;

import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.pingan.yourchats.R;
import com.pingan.yourchats.adapter.EmotionVpAdapter;

import butterknife.BindView;

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


    @Override
    public int getViewLayout() {
        return R.layout.chat_activity;
    }

    @Override
    protected void loadData() {
        emotionVp.setAdapter(new EmotionVpAdapter());
    }

}
