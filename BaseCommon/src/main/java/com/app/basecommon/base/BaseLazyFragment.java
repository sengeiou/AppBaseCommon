package com.app.basecommon.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 13:46
 * @Describe
 */

public abstract class BaseLazyFragment<P extends BasePresenter> extends BaseFragment<P> {

    private boolean mInitSuccess = false;//判断是否初始化view
    protected boolean mIsFirstVisible = true;//判断是否是第一次加载，如果加载失败，可以在实现类设置为true，当再次显示时可以重新加载


    @Override
    protected void initDataView() {

        //获得intent
        initIntent();

        //初始化控件
        initView();

        //初始化数据
        initData();

        //初始化mPresenter
        initPresenter();
    }

    /**
     * 当viewpager和fragment嵌套使用的时候，需要借助setUserVisibleHint来进行数据预加载显示
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareLoadData(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInitSuccess = true;
        prepareLoadData(getUserVisibleHint());
    }

    private void prepareLoadData(boolean isVisibleToUser){
        if (isVisibleToUser && mInitSuccess && mIsFirstVisible){//当前显示，切初始化之后再加载数据
            mIsFirstVisible = false;
            //获取数据
            getData();

        }
    }

}
