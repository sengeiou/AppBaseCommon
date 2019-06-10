package com.app.basecommon.base;

import com.app.basecommon.utiles.Utils;

import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;

/**
 * @Auther JiRui
 * @CreateTime 2019/4/23 17:48
 * @Describe
 */
public abstract class BasePresenter implements IBasePresenter {

    @Override
    public void onCreate(LifecycleOwner owner) {
        //初始化数据
        initData();

        //获取数据
        getData();
    }

    @Override
    public void onStart(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }

    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onStop(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {

    }

    @Override
    public void updataRequest() {

    }

    protected void toast(@StringRes int message) {
        Utils.toast(message);
    }

    protected void toast(String message) {
        Utils.toast(message);
    }

    protected void startAct(Class toclass) {
        Utils.startAct(toclass);
    }
}
