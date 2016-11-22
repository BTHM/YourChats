package com.pingan.yourchats.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.yourchats.R;

/**
 * Author：liupeng on 2016/11/22 15:08
 * Address：liupeng264@pingan.com.cn
 */
public class WeiXinFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weixin_fragment, container,false);
        //View view = View.inflate(getActivity(), R.layout.weixin_fragment, null);
        return view;

    }
}
