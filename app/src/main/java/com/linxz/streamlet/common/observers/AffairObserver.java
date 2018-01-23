package com.linxz.streamlet.common.observers;

/**
 * 描述：事务监听，某个类的某个事件触发对于的观察者做出相应的反应，根据tag标记
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年05月11日  10:44
 * 版本：2.0
 */

public interface AffairObserver {

    /**刷新当前数据*/
     void updateAffair(String tag, Object object);
}
