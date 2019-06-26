package com.app.commondemo;

import androidx.databinding.DataBindingUtil;

import android.view.View;

import com.app.basecommon.base.BaseActivity;
import com.app.commondemo.databinding.MainActivityBinding;
import com.app.commondemo.view1.ViewBaseActivity;

public class MainActivity extends BaseActivity {

    private MainActivityBinding mMainActivityBinding;

    public void onClickCus(View view){
        int id = view.getId();

        if (id == mMainActivityBinding.jump.getId()){
            startAct(ViewBaseActivity.class);
        }
    }

    @Override
    public void initIntent() {

    }

    @Override
    public void initView() {
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainActivityBinding.setMain(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void getData() {

    }
}
