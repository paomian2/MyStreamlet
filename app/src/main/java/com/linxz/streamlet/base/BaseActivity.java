package com.linxz.streamlet.base;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.githang.statusbar.StatusBarCompat;
import com.linxz.streamlet.R;
import com.linxz.streamlet.utils.StringUtils;
import com.linxz.streamlet.utils.ToastUtils;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 所有activity的基类
 * @author Linxz
 */
public abstract class BaseActivity extends FragmentActivity implements UncaughtExceptionHandler {
    protected String Tag = StringUtils.EMPTY;
    protected BaseActivity activity;
    private Toast toast = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    application = (AppApplication) getApplication();
        activity = this;
        AppActivityManager.getInstance().addActivity(this);
        //沉浸式状态栏
        if ("HomeActivity".equals(activity.getClass().getSimpleName())
                ||"BusinessHomeActivity".equals(activity.getClass().getSimpleName())){
            //透明状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = getWindow();
                // Translucent status bar
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }else{ //colorPrimaryDark
            StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark),false);
        }
        setTag();
        Bundle b = getIntent().getBundleExtra("Bundle");
        onGetBundle(b);
        setActivityView(savedInstanceState);
        initUI();
        initData();
    }

    public void onGetBundle(Bundle bundle) {}

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
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        AppActivityManager.getInstance().removeActivity(this);
    }

    /**未知异常处理*/
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        AppActivityManager.getInstance().cleanActivity();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置手机背景跟actionbar一样的颜色
     */
    private void setPhoneActionbarBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    /**
     * 默认时间LENGTH_LONG
     */
    public void showToast(String msg) {
       // UIHelper.showToast(this, toast, msg);
        ToastUtils.showToast(this,msg);
    }

    /**
     * @param msg:消息
     * @param length:显示时间
     */
    public void showToast(String msg, int length) {
        ToastUtils.showToast(this,msg);
    }

    /**
     * 设置Activity对于的View，并初始化布局View的子控件
     * */
    protected abstract void setActivityView(Bundle bundle);

    /**
     * 初始化View的逻辑操作，事件监听
     * */
    protected abstract void initUI();

    /**初始化View对应的数据，发起网络请求等*/
    protected abstract void initData();

    public String setTag(){
        return "";
    }

}
