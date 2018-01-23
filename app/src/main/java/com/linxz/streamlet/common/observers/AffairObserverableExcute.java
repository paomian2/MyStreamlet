package com.linxz.streamlet.common.observers;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：事务执行者
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年05月11日  10:53
 * 版本：2.0
 */

public class AffairObserverableExcute implements AffairObserverable{

    private static AffairObserverableExcute instance;

    /**收集全部的观察者*/
    private static List<AffairObserver> affairObserverList=new ArrayList<>();

    private AffairObserverableExcute(){}

    public static AffairObserverableExcute getInstance(){
        if (instance==null){
            synchronized (AffairObserverableExcute.class){
                if (instance==null){
                    instance=new AffairObserverableExcute();
                }
            }
        }
        return instance;
    }


    @Override
    public void attach(AffairObserver observer) {
        affairObserverList.add(observer);
    }

    @Override
    public void detach(AffairObserver observer) {
        affairObserverList.remove(observer);
    }

    @Override
    public void notifyAffairObserver(String tag, Object object) {
        for (AffairObserver observer:affairObserverList){
            observer.updateAffair(tag,object);
        }
    }
}
