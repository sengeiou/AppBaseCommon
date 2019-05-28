package com.app.basecommon.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.basecommon.utiles.Utils;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 13:48
 * @Describe
 */
public interface IMethed {

    /**
     * fragment 使用需要返回一个DataBinding.getRoot的view，或者inflater的view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    View getLayoutView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 获得intent
     */
    void initIntent();

    /**
     * 初始化控件
     * activity 和 fragment 都有使用
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化mPresenter
     */
    void initPresenter();

    /**
     * 获取数据
     */
    void getData();

    void toast(@StringRes int message);

    void toast(String message);

    void startAct(Class toclass);
}
