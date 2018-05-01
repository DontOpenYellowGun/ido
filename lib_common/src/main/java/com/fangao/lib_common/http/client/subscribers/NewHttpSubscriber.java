package com.fangao.lib_common.http.client.subscribers;

import com.fangao.lib_common.http.client.subscribers.exception.ApiException;
import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.http.client.subscribers.exception.NetException;
import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/9/26.
 */

public abstract class NewHttpSubscriber<T> implements Observer<BaseEntity<T>> {

    private static final String TAG = "HttpSubscriber";

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Disposable disposable = d;
        if (!NetUtil.INSTANCE.isConnected()) {
            if (!disposable.isDisposed()) {
                onError(new NetException("网络未连接！"));
            }
        }
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        if (tBaseEntity.isSuccess()) {
            onSuccess(tBaseEntity);
        } else {
            onError(new ApiException(tBaseEntity.getDescribe()));
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof Exception) {
            //访问获得对应的Exception
            onFail(ExceptionHandle.handleException(e));
        } else {
            //将Throwable 和 未知错误的status code返回
            onFail(new ExceptionHandle.ResponseThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(BaseEntity<T> t);

    protected abstract void onFail(ExceptionHandle.ResponseThrowable responseThrowable);

}
