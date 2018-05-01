package com.dreambuilder.module_main.support.api;

import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.model.User;
import com.fangao.module_main.model.Dict;
import com.fangao.module_main.model.Group;
import com.fangao.module_main.model.Tool;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;


/**
 * 文件描述：
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public interface Api {

    @FormUrlEncoded
    @POST("user/query/1")
    Observable<BaseEntity<List<User>>> findUser(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("user/other/find/1")
    Observable<BaseEntity<User>> getUserDetailInfo(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("friend/request/1")
    Observable<BaseEntity> sendFriendRequest(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("friend/requests/1")
    Observable<BaseEntity<List<User>>> getFriendRequest(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("friend/dealRequest/1")
    Observable<BaseEntity<User>> dealWithFriendRequest(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("friend/list/1")
    Observable<BaseEntity<List<User>>> getFriendList(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/list/1")
    Observable<BaseEntity<List<Group>>> getGroupList(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/create/1")
    Observable<BaseEntity<Group>> createGroup(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/member/list/1")
    Observable<BaseEntity<List<User>>> getGroupMembers(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/update/1")
    Observable<BaseEntity> updateGroupInfo(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/member/add/1")
    Observable<BaseEntity> addGroupMember(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/member/delete/1")
    Observable<BaseEntity> deleteGroupMember(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/delete/1")
    Observable<BaseEntity> deleteGroup(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("group/member/exit/1")
    Observable<BaseEntity> exitGroup(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("user/find/1")
    Observable<BaseEntity<User>> getCurrentUser(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("msg/private/send/1")
    Observable<BaseEntity> sendPrivetaMsg(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("msg/group/send/1")
    Observable<BaseEntity> sendGroupMsg(@FieldMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("user/update/1")
    Observable<BaseEntity> updateUser(@FieldMap() Map<String, Object> params);

    @Multipart
    @POST("self/account/upload/1")
    Observable<BaseEntity<JsonObject>> uploadAvatar(@QueryMap Map<String, Object> map, @Part() List<MultipartBody.Part> parts);

    @Multipart
    @POST("sys/upload/1")
    Observable<BaseEntity<JsonObject>> upLoadFiles(@QueryMap Map<String, Object> map, @Part() List<MultipartBody.Part> parts);

    @FormUrlEncoded
    @POST("sys/advise/1")
    Observable<BaseEntity> reply(@FieldMap() Map<String, Object> params);

    @GET("sys/msm/send/1")
    Observable<Object> getVerifyCode(@QueryMap() Map<String, Object> params);

    @GET("sys/dict/1")
    Observable<BaseEntity<List<Dict>>> getDict(@QueryMap() Map<String, Object> params);

    @GET("user/questions/1")
    Observable<BaseEntity<List<Dict>>> getQuestion(@QueryMap() Map<String, Object> params);

    @GET("user/save/question/1")
    Observable<BaseEntity> saveQuestion(@QueryMap() Map<String, Object> params);

    @GET("user/check/question/1")
    Observable<BaseEntity> checkQuestion(@QueryMap() Map<String, Object> params);

    @GET("friend/delete/1")
    Observable<BaseEntity> deleteFriend(@QueryMap() Map<String, Object> params);

    @GET("health/record/quick/list/1")
    Observable<BaseEntity<List<Tool>>> getTools(@QueryMap() Map<String, Object> params);

    @FormUrlEncoded
    @POST("friend/update/1")
    Observable<BaseEntity> updateFriendInfo(@FieldMap() Map<String, Object> params);
}

