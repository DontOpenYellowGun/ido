package com.fangao.lib_common.event;

/**
 * Created by Sven on 2017/5/16.
 */

public class CommonEvent<T> {

    private String tag;
    private T message;

    public CommonEvent(String tag) {
        this.tag = tag;
    }

    public CommonEvent(String tag, T message) {
        this.tag = tag;
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
