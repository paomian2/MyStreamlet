/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * 2012-2-10 		turlet@163.com
 * <p>
 * Copyright (c) 2012,  All Rights Reserved.
 */

package com.linxz.streamlet.utils;

import android.util.Log;

/**
 * 全局日志管理类<BR>
 * [功能详细描述]
 */
public class LogUtil {

    public static final String TAG = "Linxz";
    /**是否开发模式*/
    public static boolean DEBUG =PhoneUtil.isApkDebugable();

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(TAG + tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(TAG + tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(TAG + tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(TAG + tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(TAG + tag, msg);
        }
    }

    public static void log(Throwable t) {
        if (DEBUG && t != null) {
            t.printStackTrace();
        }
    }

}
