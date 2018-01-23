package com.linxz.streamlet.mvp.view.frags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxz.streamlet.R;
import com.linxz.streamlet.base.BaseActivity;
import com.linxz.streamlet.base.view.BaseMVPFragment;
import com.linxz.streamlet.mvp.presenter.HomeIndexPresenter;

/**
 * <p>
 * Function： 首页
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月23日  10:49	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class HomeIndexFrag extends BaseMVPFragment<HomeIndexPresenter> implements HomeIndexView{


    @Override
    protected HomeIndexPresenter createPresenter() {
        return new HomeIndexPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.frag_mine,container,false);
       super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }

    @Override
    public BaseActivity getMyContext() {
        return activity;
    }
}
