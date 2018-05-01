package com.fangao.lib_common.http.client.subscribers.func;

import com.fangao.lib_common.http.client.subscribers.exception.ApiException;

import io.reactivex.functions.Function;

/**
 * 文件描述：直接拿到result
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public class ApiHandle<T> implements Function<BaseEntity<T>, T> {

    @Override
    public T apply(BaseEntity<T> entity) throws Exception {
        if (!entity.isSuccess()) {
            throw new ApiException(entity.getDescribe());
        } else {
            return entity.getResult();
        }
    }
}
