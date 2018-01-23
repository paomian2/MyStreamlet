package com.linxz.streamlet.mvp.presenter;
import com.linxz.streamlet.base.presenter.BasePresenter;
import com.linxz.streamlet.mvp.module.InternetModule;
import com.linxz.streamlet.mvp.view.HomeView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月23日  10:44	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class HomePresenter extends BasePresenter<HomeView,InternetModule>{

    public HomePresenter(HomeView mView) {
        super(mView);
    }

    @Override
    public InternetModule createModel() {
        return new InternetModule();
    }
}
