package com.pingan.yourchats.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：liupeng on 2016/11/23 17:56
 * Address：liupeng264@pingan.com.cn
 */
public abstract class BaseFragment extends Fragment implements LazyLoadView{
    private boolean IsInitView=false;
    private boolean isLoad=false;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(setContentView(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        IsInitView = true;
        isCanLoadData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!IsInitView) {
            return;
        }
        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }



    /**
     * 视图销毁的时候讲Fragment是否初始化的状态变为false
     */
    @Override
    public void onDestroyView() {

        IsInitView = false;
        isLoad = false;
        mUnbinder.unbind();
        super.onDestroyView();
    }

    public void stopLoad() {
        // 不是必须
    }
}
