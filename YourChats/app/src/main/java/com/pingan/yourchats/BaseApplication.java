package com.pingan.yourchats;

import android.app.Application;

/**
 * Author：liupeng on 2016/11/22 11:21
 * Address：liupeng264@pingan.com.cn
 */
public class BaseApplication extends Application {

    private static BaseApplication mBaseApplication;

    public BaseApplication() {
    }
    public static BaseApplication getInstance() {
            synchronized (BaseApplication.class){
                if (mBaseApplication == null) {
                    mBaseApplication = new BaseApplication();
                }
                
                return mBaseApplication;

            }
    }
}
