package com.fangao.lib_common.http.client.Interceptor;

import com.fangao.lib_common.http.client.requestbody.CountingRequestBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/12/6.
 */

public class UpLoadProgressInterceptor implements Interceptor {

    private CountingRequestBody.Listener progressListener;

    public UpLoadProgressInterceptor(CountingRequestBody.Listener progressListener) {
        this.progressListener = progressListener;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        if (originalRequest.body() == null) {
            return chain.proceed(originalRequest);
        }

        Request progressRequest = originalRequest.newBuilder()
                .method(originalRequest.method(),
                        new CountingRequestBody(originalRequest.body(), progressListener))
                .build();

        return chain.proceed(progressRequest);
    }
}
