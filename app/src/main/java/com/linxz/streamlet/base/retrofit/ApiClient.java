package com.linxz.streamlet.base.retrofit;
import com.linxz.streamlet.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  18:12
 * 版本：2.0
 */

public class ApiClient {
    public static Retrofit mRetrofit;
    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient defaultHttpClient;
            if (true){
                defaultHttpClient= new OkHttpClient.Builder()
                        //ok3添加拦截器,打印日志
                        .addNetworkInterceptor(new LoggingInterceptor()).build();
            }else{
                defaultHttpClient=new OkHttpClient.Builder().build();
            }

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(defaultHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}
