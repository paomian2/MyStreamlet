package com.linxz.streamlet.mvp.view.frags;
import com.linxz.streamlet.base.BaseActivity;
import com.linxz.streamlet.base.view.BaseMVPFragment;
import com.linxz.streamlet.mvp.presenter.HomeMinePresenter;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月22日  11:35	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class HomeMineFrag extends BaseMVPFragment<HomeMinePresenter> implements HomeMineView {


    @Override
    public BaseActivity getMyContext() {
        return activity;
    }

    @Override
    protected HomeMinePresenter createPresenter() {
        return new HomeMinePresenter(this);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }
}
