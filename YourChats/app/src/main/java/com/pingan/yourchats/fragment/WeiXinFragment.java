package com.pingan.yourchats.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.pingan.yourchats.activity.ChatActivity;
import com.pingan.yourchats.R;

import butterknife.BindView;

/**
 * Author：liupeng on 2016/11/22 15:08
 * Address：liupeng264@pingan.com.cn
 */
public class WeiXinFragment extends BaseFragment {

    @BindView(R.id.to_chat)
    LinearLayout toChat;

    @Override
    public void lazyLoad() {
        toChat.findViewById(R.id.to_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到聊天界面
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int setContentView() {
        return R.layout.weixin_fragment;
    }


}
