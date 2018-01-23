package com.linxz.streamlet.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtil {
    private static SharedPreferenceUtil sharedPreferenceUtil;
    /**用户资料*/
    private static SharedPreferences sharedPreferences;
    private final static String KEY = "dbgs_sharedpreferences";
    /**引导页*/
    public final static String GUIDE = "guide";
    public final static String USERINFO="userInfo";
    public final static String IMEI="IMEI";
    public final static String TOKEN="token";
    public final static String GetSignKey="GetSignKey";
    /**关键字搜索历史记录*/
    public final static String SEARCH_HISTORY="searchHistory";


    /**清除全部缓存*/
    public static void clearCookie(Activity activity){
        SharedPreferenceUtil sharedPreferenceUtil=getInstance(activity);
        sharedPreferenceUtil.putString(USERINFO,"");
        sharedPreferenceUtil.putString(TOKEN,"");
        sharedPreferenceUtil.putString(TOKEN,"");
        sharedPreferenceUtil.putString(SEARCH_HISTORY,"");
    }

    protected SharedPreferenceUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtil getInstance(Context context) {
        if (sharedPreferenceUtil == null) {
            sharedPreferenceUtil = new SharedPreferenceUtil(context);
        }
        return sharedPreferenceUtil;
    }

    /**
     * 设置String类型值
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 设置long类型值
     *
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 设置int类型值
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 设置Boolean类型值
     *
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 设置Float类型值
     *
     * @param key
     * @param value
     */
    public void putFloat(String key, float value) {
        Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 读取String类型值，默认为""
     *
     * @param key
     */
    public String getString(String key) {
        return sharedPreferences == null ? "" : sharedPreferences.getString(key, "");
    }

    /**
     * 读取boolean类型值，默认为false;
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key, boolean deafultValue) {
        return sharedPreferences.getBoolean(key, deafultValue);
    }

    /**
     * 读取int类型值，默认为0
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * 读取long类型值，默认为0
     *
     * @param key
     * @return
     */
    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    /**
     * 读取float类型值，默认为0
     *
     * @param key
     * @return
     */
    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    /**
     * 判断是否存在此字段
     */
    public boolean has(String key) {
        return sharedPreferences.contains(key);
    }

    /**移除某个字段*/
    public void remove(String key) {
        Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }





}

