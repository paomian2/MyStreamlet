package com.linxz.streamlet.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 描述：
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  16:19
 * 版本：2.0
 */

public class ToastUtils {

    private static Toast mToast;

    public static void showToast(Context context, String msg){
        if (mToast==null){
            mToast= Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
