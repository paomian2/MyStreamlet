package com.linxz.streamlet.common.observers;

/**
 * 描述：事务被观察者， 某一事务触发 {@link AffairObserver}
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年05月11日  10:46
 * 版本：2.0
 */

public interface AffairObserverable {
    /**订阅*/
     void attach(AffairObserver buyOrderObserver);

    /**取消订阅*/
     void detach(AffairObserver buyOrderObserver);

    /**通知观察者，订单实体进行了一定的操作*/
     void notifyAffairObserver(String tag, Object object);
}
