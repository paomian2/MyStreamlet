package com.linxz.streamlet.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxz.streamlet.base.BaseFragment;
import com.linxz.streamlet.base.presenter.BasePresenter;

public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment {

    public P mvpPresenter;

    protected abstract P createPresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public String setTag() {
        return null;
    }
}
