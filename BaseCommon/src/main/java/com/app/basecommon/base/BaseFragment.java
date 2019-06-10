package com.app.basecommon.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.basecommon.utiles.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 13:46
 * @Describe
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IMethed {
    //P层的应用
    protected P mPresenter;

    protected View mInflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView(inflater, container, savedInstanceState) != null) {
            mInflate = getLayoutView(inflater, container, savedInstanceState);
            return mInflate;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据和控件
        initDataView();
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//用户切换fragment时需要刷新数据使用
            updataRequest();
        }
    }

    protected void initDataView() {

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

    protected void updataRequest() {
        if (mPresenter != null){
            mPresenter.updataRequest();
        }
    }

    @Override
    public void toast(int message) {
        Utils.toast(message);
    }

    @Override
    public void toast(String message) {
        Utils.toast(message);
    }


}
