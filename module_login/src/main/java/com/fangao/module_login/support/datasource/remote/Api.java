package com.fangao.module_login.support.datasource.remote;

import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.model.User;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 文件描述：开单相关接口
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public interface Api {

    @GET("sys/init/1")
    Observable<BaseEntity<InitData>> init(@QueryMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/login/1")
    Observable<BaseEntity<User>> login(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/register/1")
    Observable<BaseEntity> register(@FieldMap() Map<String, Object> params);

    @GET("auth/logout/1")
    Observable<BaseEntity<Object>> logout(@QueryMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/passwd/change/1")
    Observable<BaseEntity<Object>> changePassword(@FieldMap() Map<String, Object> params);

    @GET("sys/msm/send/1")
    Observable<BaseEntity> getVerifyCode(@QueryMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("sys/code/check/1")
    Observable<BaseEntity> checkCode(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("user/phone/check/1")
    Observable<BaseEntity> checkUserName(@FieldMap() Map<String, Object> params);

}

