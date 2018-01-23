package com.linxz.streamlet.base.retrofit;
import com.linxz.streamlet.base.BaseResponse;
import com.linxz.streamlet.utils.LogUtil;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 描述：网络返回结果统一处理
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  18:16
 * 版本：3.0
 */

public abstract class ApiCallback<M extends BaseResponse> extends Subscriber<M> {

    public static final int CODE_504=504;
    public static final int CODE_502=502;
    public static final int CODE_404=404;

    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            LogUtil.d("" + this.getClass().getSimpleName(), "code=" + code);
            if (code ==CODE_504) {
                msg = "网络不给力";
            }
            if (code == CODE_502 || code == CODE_404) {
                msg = "服务器异常，请稍后再试";
            }
            LogUtil.d("ApiCallback failure:--->>", msg);
            onFailure(msg);
        } else {
            LogUtil.d("ApiCallback failure:--->>", e.getMessage());
            onFailure(e == null ? "网络异常" : e.getMessage() + "");
        }
        onFinish();
    }

}
