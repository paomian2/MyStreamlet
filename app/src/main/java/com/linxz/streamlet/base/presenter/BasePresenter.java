package com.linxz.streamlet.base.presenter;

import com.linxz.streamlet.base.model.BaseModel;
import com.linxz.streamlet.base.view.BaseView;

/**
 * 描述：
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  18:02
 * 版本：2.0
 */

public abstract class BasePresenter<T extends BaseView,M extends BaseModel> {

    public T mView;
    public M mModel;

   public BasePresenter(T mView){
       attachView(mView);
       mModel=createModel();
   }

    public void attachView(T mView){
        this.mView=mView;
    }

    public void detachView(){
        if (mModel!=null){
            mModel.onUnsubscribe();
        }
        this.mView=null;
    }

    public abstract M createModel();


}
