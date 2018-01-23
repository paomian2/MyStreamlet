package com.linxz.streamlet.mvp.presenter;
import com.linxz.streamlet.base.presenter.BasePresenter;
import com.linxz.streamlet.mvp.module.InternetModule;
import com.linxz.streamlet.mvp.view.frags.HomeMineView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月22日  11:36	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class HomeMinePresenter extends BasePresenter<HomeMineView,InternetModule>{

    public HomeMinePresenter(HomeMineView mView) {
        super(mView);
    }

    @Override
    public InternetModule createModel() {
        return new InternetModule();
    }
}
