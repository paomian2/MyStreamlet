package com.linxz.streamlet.mvp.presenter;

import com.linxz.streamlet.base.presenter.BasePresenter;
import com.linxz.streamlet.mvp.module.InternetModule;
import com.linxz.streamlet.mvp.view.frags.HomeIndexView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月23日  10:49	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class HomeIndexPresenter extends BasePresenter<HomeIndexView,InternetModule>{

    public HomeIndexPresenter(HomeIndexView mView) {
        super(mView);
    }

    @Override
    public InternetModule createModel() {
        return new InternetModule();
    }
}
