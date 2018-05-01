package com.fangao.lib_common.http.server;


import com.fangao.lib_common.base.BaseApplication;
import com.fangao.lib_common.constants.Domain;
import com.fangao.lib_common.http.client.RetrofitClient;

/**
 * 未登录之前调用接口地址
 * Created by Sven on 2017/4/25.
 */

public enum Service {

    INSTANCE;

    private Api mApi;

    Service() {
        mApi = RetrofitClient.INSTANCE
                .getRetrofitBuilder()
                .baseUrl(Domain.getBaseUrl(BaseApplication.getInstance())).build()
                .create(Api.class);
    }

    public Api getApi() {
        return mApi;
    }


}
