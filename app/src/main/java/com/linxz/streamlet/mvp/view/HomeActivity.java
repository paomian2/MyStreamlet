package com.linxz.streamlet.mvp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.linxz.streamlet.R;
import com.linxz.streamlet.adapter.HomePagerAdapter;
import com.linxz.streamlet.base.BaseActivity;
import com.linxz.streamlet.base.view.BaseMVPActivity;
import com.linxz.streamlet.common.widget.NoScrollViewPager;
import com.linxz.streamlet.mvp.presenter.HomePresenter;
import com.linxz.streamlet.mvp.view.frags.HomeIndexFrag;
import com.linxz.streamlet.mvp.view.frags.HomeItmsgFrag;
import com.linxz.streamlet.mvp.view.frags.HomeMineFrag;
import com.linxz.streamlet.mvp.view.frags.HomeMsgFrag;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年01月22日  11:34	lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class HomeActivity extends BaseMVPActivity<HomePresenter> implements HomeView {


    @Bind(R.id.viewPager)
    NoScrollViewPager viewPager;
    @Bind(R.id.rg_container)
    RadioGroup rgContainer;


    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void setActivityView(Bundle bundle) {
        setContentView(R.layout.act_home);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        fragmentList.add(new HomeIndexFrag());
        fragmentList.add(new HomeItmsgFrag());
        fragmentList.add(new HomeMsgFrag());
        fragmentList.add(new HomeMineFrag());
        viewPager.setOffscreenPageLimit(4);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(homePagerAdapter);
        viewPager.setCurrentItem(0);
        rgContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                viewPager.setCurrentItem(i);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public BaseActivity getMyContext() {
        return activity;
    }


}
