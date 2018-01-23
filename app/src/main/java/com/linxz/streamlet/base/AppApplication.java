package com.linxz.streamlet.base;

import android.app.ActivityManager;
import android.app.Application;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

public class AppApplication extends Application {

    private static AppApplication mInstance;


    public static AppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initPreIMSetting();
        initIM();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private void initPreIMSetting() {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(getInstance().getPackageName())) {
            Log.e("", "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
    }

    private void initIM() {
/*        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        //   options.setAcceptInvitationAlways(true);
        //初始化
        EMClient.getInstance().init(getInstance(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);*/
    }

    public String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo)
                    (i.next());
            try {
                if (info.pid == pID) {
                    return processName;
                }
            } catch (Exception e) {
                 Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return "";
    }

}
