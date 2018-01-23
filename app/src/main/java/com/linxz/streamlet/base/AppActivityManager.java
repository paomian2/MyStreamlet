package com.linxz.streamlet.base;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import com.linxz.streamlet.R;
import com.linxz.streamlet.utils.SharedPreferenceUtil;
import java.util.LinkedList;

/**
 * 这是一个单例模式的 activity管理器，没创建一个activity就加入到这个栈内，finish就将 它移除，
 * 当应用退出时，遍历栈内activity，并且finish退出
 */
public class AppActivityManager {
	private static AppActivityManager instance = null;
	private LinkedList<Activity> acts;

	private AppActivityManager() {
		acts = new LinkedList<Activity>();
	};

	public static AppActivityManager getInstance() {
		if (instance == null) {
			instance = new AppActivityManager();
		}
		return instance;
	}

	public void goTo(Activity self, Class<? extends Activity> cls) {
		Intent it = new Intent(self, cls);
		self.startActivity(it);
	}

	public void goTo(Activity self, Intent it) {
		self.startActivity(it);
	}

	public void goFoResult(Activity self, Class<? extends Activity> cls, int RequestCode) {
		Intent it = new Intent(self, cls);
		goFoResult(self, it, RequestCode);
	}

	public void goFoResult(Activity self, Intent it, int RequestCode) {
		self.startActivityForResult(it, RequestCode);
	}

	public void goFoResultBottom(Activity self, Class<? extends Activity> cls, int RequestCode) {
		Intent it = new Intent(self, cls);
		goFoResultBottom(self, it, RequestCode);
	}

	public void goFoResultBottom(Activity self, Intent it, int RequestCode) {
		self.startActivityForResult(it, RequestCode);
		self.overridePendingTransition(R.anim.dock_bottom_enter, R.anim.dock_bottom_exit);
	}

	public void addActivity(Activity act) {
		acts.add(act);
	}

	public void removeActivity(Activity act) {
		if (acts != null && acts.indexOf(act) >= 0) {
			acts.remove(act);
		}
	}

	public void cleanActivity() {
		while (acts.size() != 0) {
			Activity act = acts.poll();
			act.finish();
		}
	}


	public Activity getTopActivity() {
		return (acts == null || acts.size() <= 0) ? null : acts.get(acts.size() - 1);
	}

	/**
	 *    1.关闭推送信息
	 *    2.清空全部缓存信息
	 *    3.退出全部activity
	 *    4.进入登录页面
	 * */
	public void quit(Activity context) {
		//关闭推送
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancelAll();
		// 清除临时文件
		//清空Shareprefrence文件
		SharedPreferenceUtil.clearCookie(context);
		while (acts.size() != 0) {
			Activity act = acts.poll();
			if (act!=context){
				act.finish();
			}
		}
	}

	/**退出应用*/
	public void exit(){
		cleanActivity();
		System.exit(0);
	}
}
