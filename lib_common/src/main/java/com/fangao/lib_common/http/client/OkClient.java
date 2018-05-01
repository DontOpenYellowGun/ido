package com.fangao.lib_common.http.client;


import android.support.annotation.NonNull;
import android.util.Log;

import com.fangao.lib_common.BuildConfig;
import com.fangao.lib_common.base.BaseApplication;
import com.fangao.lib_common.http.client.Interceptor.UpLoadProgressInterceptor;
import com.fangao.lib_common.http.client.requestbody.CountingRequestBody;
import com.fangao.lib_common.util.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 文件描述：OkHttpClient
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public enum OkClient {

    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final String TAG = "OkClient";

    OkClient() {

        short HTTP_CONNECT_TIMEOUT = 10;
        short HTTP_READ_TIMEOUT = 30;
        short HTTP_WRITE_TIMEOUT = 10;

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(getCacheInterceptor())
                .addNetworkInterceptor(getCacheInterceptor())
                .addInterceptor(getProgressInterceptor())
                .cache(configCache())
                .build();
    }

    @NonNull
    private UpLoadProgressInterceptor getProgressInterceptor() {
        return new UpLoadProgressInterceptor(new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(long bytesWritten, long contentLength) {
                if (mProgressCallBack != null) {
                    mProgressCallBack.onRequestProgress(bytesWritten, contentLength);
                }
            }
        });
    }

    /**
     * Method:      configCache <br>
     * Description: 配置缓存 <br>
     * Creator:     sven <br>
     * Date:        2017/11/4 下午12:39
     */
    @NonNull
    private Cache configCache() {
        File httpCacheDirectory = new File(BaseApplication.getInstance().getCacheDir(), "http-cache");
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(httpCacheDirectory, cacheSize);
    }


    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


    @NonNull
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (!NetUtil.INSTANCE.isConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response originalResponse = chain.proceed(request);
                if (NetUtil.INSTANCE.isConnected()) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached")
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };

    }

    private ProgressCallBack mProgressCallBack;

    public ProgressCallBack getmProgressCallBack() {
        return mProgressCallBack;
    }

    public void setmProgressCallBack(ProgressCallBack mProgressCallBack) {
        this.mProgressCallBack = mProgressCallBack;
    }

    public interface ProgressCallBack {
        void onRequestProgress(long bytesWritten, long contentLength);
    }
}
