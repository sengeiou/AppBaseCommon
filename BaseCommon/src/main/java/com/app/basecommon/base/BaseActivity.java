package com.app.basecommon.base;

import android.os.Bundle;

import com.app.basecommon.utiles.Utils;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Auther JiRui
 * @CreateTime 2019/4/23 17:06
 * @Describe
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity{

    //P层的应用
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataView();
        getLifecycle().addObserver(mPresenter);
    }

    private void initDataView() {

        //获得intent
        initIntent();

        //初始化控件
        initView();

        //初始化数据
        initData();

        //初始化mPresenter
        initPresenter();

        //获取数据
        getData();

    }

    protected void initIntent() {
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void getData();

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
