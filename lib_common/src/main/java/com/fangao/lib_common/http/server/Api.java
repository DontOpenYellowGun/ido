package com.fangao.lib_common.http.server;

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
 * 文件描述：
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public interface Api {

    @GET("sys/init/1")
    Observable<BaseEntity<InitData>> init(@QueryMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/login/1")
    Observable<BaseEntity<User>> login(@FieldMap() Map<String, Object> params);

    @GET("auth/logout/1")
    Observable<BaseEntity<Object>> logout(@QueryMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/passwd/change/1")
    Observable<BaseEntity<Object>> changePassword(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/passwd/setting/1")
    Observable<BaseEntity> findPassword(@FieldMap() Map<String, Object> params);

}

