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
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IMethed{

    //P层的应用
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataView();
        if (mPresenter != null){
            getLifecycle().addObserver(mPresenter);
        }
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

    @Override
    public void toast(int message) {
        Utils.toast(message);
    }

    @Override
    public void toast(String message) {
        Utils.toast(message);
    }


    protected void startAct(Class toclass) {
        Utils.startAct(toclass);
    }
}
