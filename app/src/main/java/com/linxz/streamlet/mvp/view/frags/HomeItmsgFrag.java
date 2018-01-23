package com.linxz.streamlet.mvp.view.frags;

import com.linxz.streamlet.base.BaseActivity;
import com.linxz.streamlet.base.view.BaseMVPFragment;
import com.linxz.streamlet.mvp.presenter.HomeItmsgPresenter;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月23日  10:55	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class HomeItmsgFrag extends BaseMVPFragment<HomeItmsgPresenter> implements HomeItmsgView{


    @Override
    protected HomeItmsgPresenter createPresenter() {
        return new HomeItmsgPresenter(this);
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
