package com.linxz.streamlet.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.linxz.streamlet.BuildConfig;
import com.linxz.streamlet.R;
import com.linxz.streamlet.base.BaseActivity;
import com.linxz.streamlet.common.widget.CustomProgressDialog;
import com.linxz.streamlet.common.widget.ProgressWheel;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class UIHelper {

    public static final int ACT_TRAN_HEAD = 43;

    public static UIHelper getInstance() {
        return new UIHelper();
    }

    /**
     * 获取屏幕分辨率：宽
     *
     * @param context
     * @return
     */
    public static int getScreenPixWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        if (!(context instanceof Activity)) {
            dm = context.getResources().getDisplayMetrics();
            return dm.widthPixels;
        }

        WindowManager wm = ((Activity) context).getWindowManager();
        if (wm == null) {
            dm = context.getResources().getDisplayMetrics();
            return dm.widthPixels;
        }

        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕分辨率：高
     *
     * @param context
     * @return
     */
    public static int getScreenPixHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        if (!(context instanceof Activity)) {
            dm = context.getResources().getDisplayMetrics();
            return dm.heightPixels;
        }

        WindowManager wm = ((Activity) context).getWindowManager();
        if (wm == null) {
            dm = context.getResources().getDisplayMetrics();
            return dm.heightPixels;
        }

        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;

    }


    /**
     * 默认时间LENGTH_SHORT
     *
     * @param msg
     */
    public static void showToast(Context context, Toast toast, String msg) {
        showToast(context, toast, msg, Toast.LENGTH_SHORT);
    }

    /***
     * @param msg
     * @param length 显示时间
     */
    public static void showToast(Context context, Toast toast, String msg, int length) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, length);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 获取当前手机的独立像素
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * dp2px
     */
    public static int dip2px(Context context, float dipValue) {
        return (int) (dipValue * getDensity(context) + 0.5f);
    }

    /**
     * px2dp
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5f);
    }


    // 获取当前版本号
    public static int getVersionCode(Context context) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = packInfo.versionCode;
            return versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 获取当前版本号
    public static String getVersionName(Context context) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo;
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            String versionName = packInfo.versionName;
            return versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /********************************************
     * 滚动条对话框
     ***********************************************/

    public static CustomProgressDialog dialog;

    public static Dialog showProgressDialog(Context context, String message) {
        try {
            if (dialog != null) {
                dialog.cancel();
                dialog = null;
            }
            dialog = CustomProgressDialog.createDialog(context);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMessage(message);
            dialog.show();
            return dialog;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static Dialog showProgressDialog(Context context) {
        return showProgressDialog(context, null);
    }

    // 去掉进度条
    public static void cancleProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
        }
    }

    /**
     * /* * 滑动选择
     */
    private static String wheelTemp = "";
    private static int mposition = 0;

    public static Map<String, Object> convertBeanToMap(Object bean) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = bean.getClass().getDeclaredFields();
        HashMap<String, Object> data = new HashMap<String, Object>();
        for (Field field : fields) {
            field.setAccessible(true);
            data.put(field.getName(), field.get(bean));
        }
        return data;
    }

    public static void openBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        LogUtil.d("down", "" + url);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    public static void cancleAllNotification(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancelAll();
    }

    public interface OnDialogClickListener {
        public void onClick();
    }

    /**
     * 带文本数据返回的Dialog右键监听
     */
    public interface OnDialogClickForResultListener {
        public void onClick(String msg);
    }


    /**
     * 版本更新，显示加载的进度框
     */
    public static void showProgress(Context context, int maxProgress, OnDialogClickListener loadListener) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        //设置水平的进度
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(maxProgress);
        progressDialog.show();
        loadListener.onClick();
    }


    public static void initLoadView(SwipeRefreshLayout mSwipeLayout, final ProgressWheel progressWheel) {
        mSwipeLayout.setColorSchemeResources(R.color.top_bg);
        mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        mSwipeLayout.setEnabled(false);
        progressWheel.spin();
        progressWheel.setBarWidth(dip2px(mSwipeLayout.getContext(), 2));
    }

    public static void initLoadView(SwipeRefreshLayout mSwipeLayout) {
        mSwipeLayout.setColorSchemeResources(R.color.top_bg);
        mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        mSwipeLayout.setEnabled(false);
    }

    public static void initRefreshView(SwipeRefreshLayout mSwipeLayout) {
        mSwipeLayout.setColorSchemeResources(R.color.top_bg);
        mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
    }

    /**
     * 带默认图
     */
    public static void imageNet(Context context, String url, ImageView view, boolean isLocal, int defaltIcon) {
        if (!StringUtils.isEmpty(url) && url.startsWith("http")) {
            imageNet2(context, url, view, isLocal, defaltIcon);
        } else {
            Glide.with(context).load(isLocal ? url : getImgUrl(url)).diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().placeholder(defaltIcon).into(view);
        }

    }

    /**
     * 不带默认图
     */
    public static void imageNet(Context context, String url, ImageView view, boolean isLocal) {
        if (!StringUtils.isEmpty(url) && url.startsWith("http")) {
            imageNet2(context, url, view, isLocal);
        } else {
            Glide.with(context).load(isLocal ? url : getImgUrl(url)).diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(view);
        }

    }

    public static void imageNet2(Context context, String url, ImageView view, boolean isLocal, int defaltIcon) {
        Glide.with(context).load(isLocal ? url : url).diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().placeholder(defaltIcon).into(view);
    }

    /**
     * 不带默认图
     */
    public static void imageNet2(Context context, String url, ImageView view, boolean isLocal) {
        Glide.with(context).load(isLocal ? url : url).diskCacheStrategy(DiskCacheStrategy.SOURCE).dontAnimate().into(view);
    }

    public static String getImgUrl(String url) {
        // return Config.TEST_IMG_BASE_URL + url;
        return BuildConfig.IMAGE_SERVER_URL + url;
    }


    /**
     * 有动画效果的电话号验证
     */
    public static boolean checkPhoneAvalibleWithAnim(BaseActivity activity, TextView textView, String msg) {
        try {
            if (!StringUtils.phoneNumberValid(textView.getText().toString().toString())) {
                activity.showToast(msg);
                startShakeAnimation(textView);
                return false;
            }
        } catch (Exception e) {
            activity.showToast("请输入正确的电话号码");
            return false;
        }
        return true;
    }

    /**
     * 拨打电话
     */
    public static void callPhone(BaseActivity context, String phoneNum) {
        if (StringUtils.isEmpty(phoneNum)) {
            context.showToast("商家还未开通电话服务");
        } else {
            Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            context.startActivity(intent1);
        }
    }

    /**
     * TextView非空校验
     */
    public static boolean checkTv(BaseActivity activity, TextView... tv) {
        for (TextView textView : tv) {
            String temp = textView.getText().toString().trim();
            if (StringUtils.isEmpty(temp)) {
                return false;
            }
        }

        return true;
    }

    /**
     * TextView非空校验，空则吐司提示
     */
    public static boolean checkTv(BaseActivity activity, TextView tv, String msg) {
        String temp = tv.getText().toString().trim();
        if (StringUtils.isEmpty(temp)) {
            activity.showToast(msg);
            return false;
        }
        return true;
    }


    /**
     * TextView非空校验，空则添加晃动动画效果+吐司提示
     */
    public static boolean checkTvWithAnim(BaseActivity activity, TextView tv, String msg) {
        String temp = tv.getText().toString().trim();
        if (StringUtils.isEmpty(temp)) {
            startShakeAnimation(tv);
            activity.showToast(msg);
            return false;
        }
        return true;
    }

    /**
     * 验证TextView中的文本长度至少N个字符
     */
    public static boolean checkTvLimit(BaseActivity activity, TextView tv, int limit, String msg) {
        String temp = tv.getText().toString().trim();
        if (temp.length() < limit) {
            startShakeAnimation(tv);
            activity.showToast(msg);
            return false;
        }
        return true;
    }

    /**
     * 验证TextView中的文本长度至少N个字符
     */
    public static boolean checkTvLimit(BaseActivity activity, TextView tv, int limitlef, int limitright, String msg) {
        String temp = tv.getText().toString().trim();
        if (temp.length() < limitlef || temp.length() > limitright) {
            startShakeAnimation(tv);
            activity.showToast(msg);
            return false;
        }
        return true;
    }


    /**
     * 开始晃动动画
     */
    public static void startShakeAnimation(TextView textView) {
        if (textView.getAnimation() == null) {
            textView.setAnimation(shakeAnimation(4));
        }
        textView.startAnimation(textView.getAnimation());
    }

    /**
     * 晃动动画
     *
     * @param counts 0.5秒钟晃动多少下
     */
    public static Animation shakeAnimation(int counts) {
        TranslateAnimation animation = new TranslateAnimation(0, 10, 0, 0);
        animation.setInterpolator(new CycleInterpolator(counts));
        animation.setDuration(500);
        return animation;
    }


    /**
     * 验证手机号码是否正确，不正确则提示，并晃动窗口
     */
    public static boolean checkPhoneNumberAvailable(BaseActivity activity, TextView phoneView) {
        String phoneNum = phoneView.getText().toString().trim();
        if (StringUtils.isEmpty(phoneNum)) {
            activity.showToast("手机号不能为空");
            startShakeAnimation(phoneView);
            return false;
        } else if (!StringUtils.phoneNumberValid(phoneNum)) {
            activity.showToast("请输入正确的手机号");
            startShakeAnimation(phoneView);
            return false;
        }
        return true;
    }

    /**
     * 判断密码是否一致
     */
    public static boolean checkPwdSame(BaseActivity activity, TextView tv, TextView tvConfirm, String msg) {
        String temp = tv.getText().toString().trim();
        String tempConfirm = tvConfirm.getText().toString().trim();
        if (temp.equals(tempConfirm)) {
            return true;
        }
        activity.showToast(msg);
        return false;
    }
}
