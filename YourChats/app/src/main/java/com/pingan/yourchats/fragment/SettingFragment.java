package com.pingan.yourchats.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.yourchats.R;

/**
 * Author：liupeng on 2016/11/22 15:10
 * Address：liupeng264@pingan.com.cn
 */
public class SettingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.address_fragment, container);  一定不能这么写
        View view = inflater.inflate(R.layout.address_fragment, container,false);

        return view;

    }
}
