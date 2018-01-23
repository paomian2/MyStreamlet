
package com.linxz.streamlet.common.permission.globleDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @Description: 动态权限申请透明activity
 * @Author: fengzeyuan
 * @Date: 17/6/25 上午1:15
 * @Version: 1.0
 */
public final class XZDialogContentActivity extends Activity {


    private XZActivityLifecycleInterface mLifecycleInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int code = intent.getIntExtra(XZDialogContentActivity.class.getCanonicalName(), -1);
        mLifecycleInterface = XZDialogPage.dialogPageMap.get(code).mLifecycleInterface;
        if (mLifecycleInterface != null) {
            mLifecycleInterface.onCreate(this);
        }
    }


    /**
     * 权限设置面板，弹窗设置面板，"系统设置"权限获取面版结果返回
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mLifecycleInterface != null) {
            mLifecycleInterface.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLifecycleInterface != null) {
            mLifecycleInterface.onDestroy();
        }
    }
    
}
