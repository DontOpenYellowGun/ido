package com.fangao.lib_common.http.client.subscribers;

import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.http.client.subscribers.exception.NetException;
import com.fangao.lib_common.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/9/26.
 */

public abstract class HttpSubscriber<T> implements Observer<T> {

    private static final String TAG = "HttpSubscriber";

    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
        if (!NetUtil.INSTANCE.isConnected()) {
//            if (!disposable.isDisposed()) {
//                disposable.dispose();
//
//            }
            onError(new NetException("网络未连接！"));
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof Exception) {
            //访问获得对应的Exception
            onError(ExceptionHandle.handleException(e));
        } else {
            //将Throwable 和 未知错误的status code返回
            onError(new ExceptionHandle.ResponseThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {
    }

    protected abstract void onSuccess(T t);

    protected abstract void onError(ExceptionHandle.ResponseThrowable responseThrowable);

}
