package com.linxz.streamlet.base.view;
import android.os.Bundle;
import com.linxz.streamlet.base.BaseActivity;
import com.linxz.streamlet.base.presenter.BasePresenter;
import com.linxz.streamlet.utils.UIHelper;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;

/**
 * 描述：
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  21:20
 * 版本：3.0
 */

public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity {
    private List<Call> callList;
    public P mvpPresenter;

    protected abstract P createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mvpPresenter=createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        callCancel();//取消所有的网络请求
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading(String msg){
        UIHelper.showProgressDialog(this,msg);
    }

    public void hideLoading(){
        UIHelper.cancleProgressDialog();
    }

    public void addCalls(Call call) {
        if (callList == null) {
            callList = new ArrayList<>();
        }
        callList.add(call);
    }

    private void callCancel() {
        if (callList != null && callList.size() > 0) {
            for (Call call : callList) {
                if (!call.isCanceled()){
                    call.cancel();
                }
            }
            callList.clear();
        }
    }

}
