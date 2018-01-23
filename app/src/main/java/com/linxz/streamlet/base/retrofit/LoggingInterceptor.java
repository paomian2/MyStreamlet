package com.linxz.streamlet.base.retrofit;
import com.linxz.streamlet.utils.LogUtil;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * 描述：网络请求拦截器,主要用于打印日志调试
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月13日  15:58
 * 版本：3.0
 */

public class LoggingInterceptor implements Interceptor {

    /**
     * 在调用了response.body().string()方法之后，
     * response中的流会被关闭，
     * 我们需要创建出一个新的response给应用层处理
     */

    public static String TAG = "LogInterceptor";

    @Override
    public okhttp3.Response intercept(Chain chain){
        Request originalRequest  = chain.request();
        try{
            //请求url
            String requestURL = originalRequest .url().toString();
            Request request = null;
            long startTime = System.currentTimeMillis();
            LogUtil.d(TAG, "\n");
            LogUtil.d(TAG, "----------Start----------------");
            LogUtil.d(TAG, "| " + requestURL);
            String method = originalRequest .method();
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (originalRequest.body() instanceof FormBody) {
                    FormBody body = (FormBody) originalRequest.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    LogUtil.d(TAG, "| RequestParams:{" + sb.toString() + "}");
                } else {
                    Buffer buffer = new Buffer();
                    originalRequest.body().writeTo(buffer);
                    String oldParamsJson = buffer.readUtf8();
                   // HashMap rootMap = new Gson().fromJson(oldParamsJson, HashMap.class);  //原始参数
                    //String newJsonParams =new Gson().toJson(rootMap);  //装换成json字符串
                     request = originalRequest.newBuilder().post(RequestBody.create(MediaType.parse("application/json; " +
                            "charset=utf-8"), oldParamsJson)).header("Content-Type", "application/json").build();
                    LogUtil.d(TAG, "| RequestParams:" + oldParamsJson);
                }
            }else{
                request=originalRequest;
            }
            //释放拦截，进行网络访问
            okhttp3.Response response = chain.proceed(request);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            if (false){
                MediaType mediaType = response.body().contentType();
                String content=response.body().string();
                LogUtil.d(TAG, "| Response:" + content);
                LogUtil.d(TAG, "----------End:" + duration + "毫秒----------");
                return response.newBuilder()
                        .body(okhttp3.ResponseBody.create(mediaType, content))
                        .build();
            }else{
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
