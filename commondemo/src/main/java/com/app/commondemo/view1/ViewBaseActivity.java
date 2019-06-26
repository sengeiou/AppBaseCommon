package com.app.commondemo.view1;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.app.basecommon.base.BaseActivity;
import com.app.commondemo.R;
import com.app.commondemo.databinding.ViewBaseActivityBinding;
import com.app.commondemo.view1.model.ViewBaseModel;
import com.app.commondemo.view1.presenter.ViewBasePresenter;

/**
 * 自定view的基础篇
 */

public class ViewBaseActivity extends BaseActivity<ViewBasePresenter> {


    private ViewBaseActivityBinding mViewDataBinding;
    private ViewBaseModel mViewBaseModel;

    @Override
    public void initIntent() {

    }

    @Override
    public void initView() {

        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_base);
            mViewBaseModel = ViewModelProviders.of(this).get(ViewBaseModel.class);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new ViewBasePresenter(mViewDataBinding,mViewBaseModel);
        mViewDataBinding.setViewbase(mPresenter);
    }

    @Override
    public void getData() {

    }
}
