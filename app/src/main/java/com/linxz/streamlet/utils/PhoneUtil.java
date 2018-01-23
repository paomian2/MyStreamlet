package com.linxz.streamlet.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.linxz.streamlet.base.AppApplication;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 手机基本信息
 *
 * @author Linxz
 */
public class PhoneUtil {
    private TelephonyManager telephonyManager;
    private Context context;
    static PhoneUtil phoneinfo;
    private Map<String, Object> infoMap;

    public PhoneUtil(Context context) {
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.context = context;
        infoMap = new HashMap<>();
    }

    public static PhoneUtil getInstance(Context context) {
        if (phoneinfo == null) {
            phoneinfo = new PhoneUtil(context);
        }
        return phoneinfo;
    }

    /**
     * 手机型号
     *
     * @return
     * @time 2011-6-1 下午03:20:14
     * @author:linyg
     */
    public String getModel() {
        String model = "";
        if (infoMap.containsKey("get_model")) {
            model = (String) infoMap.get("get_model");
        } else {
            model = Build.MODEL;
            infoMap.put("get_model", model);
        }
        return model;
    }


    /**
     * 获取设备唯一码
     *
     * @param
     * @return
     */
    public String getIMEI() {
        boolean invalidDeviceID = false;
        // 获取imei号
        String deviceID = telephonyManager.getDeviceId();
        LogUtil.i("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", deviceID);
        if (deviceID == null) {
            invalidDeviceID = true;
        } else if (deviceID.length() == 0 || deviceID.startsWith("000000") || deviceID.equals
                ("0") || deviceID.startsWith("111111")
                || deviceID.startsWith("222222") || deviceID.startsWith("333333") || deviceID
                .startsWith("333333")
                || deviceID.startsWith("444444") || deviceID.startsWith("555555") || deviceID
                .startsWith("666666")
                || deviceID.startsWith("777777") || deviceID.startsWith("888888") || deviceID
                .startsWith("999999")
                || deviceID.startsWith("123456") || deviceID.startsWith("abcdef") || deviceID
                .equals("unknown")) {
            invalidDeviceID = true;
        }

        // 如果未获取到 2.2以上的系统才能使用
        if (invalidDeviceID && Integer.parseInt(Build.VERSION.SDK) >= 9) {
            try {
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class, String.class);
                deviceID = (String) (get.invoke(c, "ro.serialno", "unknown"));
            } catch (Exception e) {
            }
            LogUtil.i("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", deviceID);
            if (deviceID == null) {
                invalidDeviceID = true;
            } else if (deviceID.length() == 0 || deviceID.startsWith("000000") || deviceID.equals
                    ("0") || deviceID.startsWith("111111")
                    || deviceID.startsWith("222222") || deviceID.startsWith("333333") || deviceID
                    .startsWith("333333")
                    || deviceID.startsWith("444444") || deviceID.startsWith("555555") || deviceID
                    .startsWith("666666")
                    || deviceID.startsWith("777777") || deviceID.startsWith("888888") || deviceID
                    .startsWith("999999")
                    || deviceID.startsWith("123456") || deviceID.startsWith("abcdef") || deviceID
                    .equals("unknown")) {
                invalidDeviceID = true;
            } else {
                invalidDeviceID = false;
            }
        }

        // 以上都无法获取到，则从sharepreference和SD卡
        if (invalidDeviceID) {
            String spDeviceId = SharedPreferenceUtil.getInstance(context).getString
                    (SharedPreferenceUtil.IMEI);
            if (spDeviceId != null && !spDeviceId.equals("")) {
                deviceID = spDeviceId;
            }
            if (deviceID == null) {
                invalidDeviceID = true;
            } else if (deviceID.length() == 0 || deviceID.startsWith("000000") || deviceID.equals
                    ("0") || deviceID.startsWith("111111")
                    || deviceID.startsWith("222222") || deviceID.startsWith("333333") || deviceID
                    .startsWith("333333")
                    || deviceID.startsWith("444444") || deviceID.startsWith("555555") || deviceID
                    .startsWith("666666")
                    || deviceID.startsWith("777777") || deviceID.startsWith("888888") || deviceID
                    .startsWith("999999")
                    || deviceID.startsWith("123456") || deviceID.startsWith("abcdef") || deviceID
                    .equals("unknown")) {
                invalidDeviceID = true;
            } else {
                invalidDeviceID = false;
            }
        }

        // 自动生成，并将保存在本地
        if (invalidDeviceID) {
            deviceID = UUID.randomUUID().toString();
        }
        if (!StringUtils.isEmpty(deviceID)) {
            SharedPreferenceUtil.getInstance(context).putString(SharedPreferenceUtil.IMEI,
                    deviceID);
        }
        LogUtil.i("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", deviceID);
        return deviceID;
    }

    /**
     * 手机号读取
     */
    public String getNativePhoneNumber() {
        telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String nativePhoneNumber = null;
        nativePhoneNumber = telephonyManager.getLine1Number();
        return nativePhoneNumber;
    }


    /**
     * 获取是否debug模式
     *
     * @return
     */
    public static boolean isApkDebugable() {
        try {
            ApplicationInfo info = AppApplication.getInstance().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 获取sd卡路径
     *
     * @return
     */
    public static String getSDPath() {
        String sdDir = "";
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment
                .MEDIA_MOUNTED);
        // 判断sd卡是否存在
        if (sdCardExist) {
            // 获取跟目录
            sdDir = Environment.getExternalStorageDirectory() + "/dbgs/";
        } else {
            sdDir = "/data/data/dbgs/";
        }
        File file = new File(sdDir);
        if (!file.exists()) {
            file.mkdir();
        }
        return sdDir;
    }

    /**
     * 打卡手机软键盘
     */
    public  void openSoftkeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //设置光标在文本的最后一个字
        String draft =editText.getText().toString().trim();
        editText.setSelection(draft.length());
    }



    /**
     * 监听软件盘的打开关闭状态
     */
    public void openOrCloseSoftboard(final EditText editText, final RelativeLayout
            mainLayout) {
        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int heightDiff = mainLayout.getRootView().getHeight() - mainLayout
                                .getHeight();
                        // 说明键盘是弹出状态
                        if (heightDiff > 100) {
                        } else {
                            editText.setInputType(InputType.TYPE_NULL);
                            editText.setFocusableInTouchMode(false);
                        }
                    }
                });
    }

    /**
     * 获取手机屏幕分辨率
     */
    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取当前包名
     */
    public String getAppPakage() {
        try {
            String pkName = context.getPackageName();
            return pkName;
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取APP版本号
     */
    public static String getAppVersionCode(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionCode = context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionName;
            return versionCode;
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取APP版本名称
     */
    public String getAppVersionName() {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 判断是否需要版本更新
     *
     * @return
     */
    public boolean checkAppVersionName(String serverVersionName) {
        String appVersionName = getAppVersionName();
        int appVersion = Integer.parseInt(appVersionName.replace(".", ""));
        int serverVersion = Integer.parseInt(serverVersionName.replace(".", ""));
        if (appVersion > serverVersion) {
            return false;
        } else if (appVersion == serverVersion) {
            return false;
        } else {
            return true;
        }

    }


}
