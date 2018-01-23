package com.linxz.streamlet.utils;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 * @author Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2018年01月20日  14:18
 * 版本：V1.0
 */
public class AsyncTaskExecutorUtil {
    private static final AsyncTaskExecutorUtil INSTANCE = new AsyncTaskExecutorUtil();
    private ThreadPoolExecutor executor;

    private static final int capacity = 100000;

    private AsyncTaskExecutorUtil(){
    }

    public static void execute(Runnable task){
        if(INSTANCE.executor==null){
            synchronized(INSTANCE){
                if(INSTANCE.executor==null){
                    try{
                        INSTANCE.executor = new ThreadPoolExecutor(
                                10, 10, 30, TimeUnit.SECONDS,
                                new LinkedBlockingQueue<Runnable>(capacity),
                                new ThreadPoolExecutor.CallerRunsPolicy());
                    }catch(Throwable ex){
                        Log.d("",""+ex.getMessage());

                    }
                }
            }
        }
        try{
            INSTANCE.executor.execute(task);
        }catch(Throwable ex){
            Log.d("",""+ex.getMessage());
        }
    }


}
