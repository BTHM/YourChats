package com.pingan.yourchats.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：liupeng on 2016/11/23 18:19
 * Address：liupeng264@pingan.com.cn
 */
public abstract class BaseActivity  extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());
        mUnbinder = ButterKnife.bind(this);

        loadData();
    }

    protected abstract void loadData();

    public abstract int getViewLayout();

    @Override
    protected void onDestroy() {

        mUnbinder.unbind();
        super.onDestroy();
        
    }
}
