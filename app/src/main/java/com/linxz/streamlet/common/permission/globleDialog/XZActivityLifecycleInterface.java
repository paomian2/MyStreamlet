
package com.linxz.streamlet.common.permission.globleDialog;
import android.app.Activity;
import android.content.Intent;

interface XZActivityLifecycleInterface {

    void onCreate(Activity activity);

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);

}
