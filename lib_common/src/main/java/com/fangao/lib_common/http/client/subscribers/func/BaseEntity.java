package com.fangao.lib_common.http.client.subscribers.func;

import com.google.gson.annotations.SerializedName;

import java.util.Optional;

/**
 * 文件描述：通用Model
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
public class BaseEntity<T> {

    @SerializedName(value = "statusCode", alternate = {"status"})
    private int statusCode;
    @SerializedName(value = "message", alternate = {"msg"})
    private String message;
    @SerializedName(value = "describe", alternate = {"desc"})
    private String describe;
    @SerializedName(value = "result", alternate = {"data"})
    private T result;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccess() {
        return statusCode == 200;
    }

    public T getResult() {
        return result;
    }

}
