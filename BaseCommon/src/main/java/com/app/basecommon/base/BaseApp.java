package com.app.basecommon.base;

import android.app.Application;

import com.app.basecommon.utiles.ActivityStackManager;
import com.app.basecommon.utiles.UIUtils;
import com.app.basecommon.utiles.Utils;


/**
 * @Auther JiRui
 * @CreateTime 2019/4/23 14:39
 * @Describe
 */
public class BaseApp extends Application {

    private static BaseApp instance;

    public static BaseApp getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ActivityStackManager.getInstance().register(this);
        Utils.init(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActivityStackManager.getInstance().unRegister(this);
    }
}
