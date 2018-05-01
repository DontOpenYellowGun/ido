package com.fangao.lib_common.http.client.subscribers;

import android.content.Context;

import com.fangao.lib_common.http.client.subscribers.exception.ApiException;
import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.http.client.subscribers.exception.NetException;
import com.fangao.lib_common.http.client.subscribers.progress.ProgressCancelListener;
import com.fangao.lib_common.http.client.subscribers.progress.ProgressDialogHandler;
import com.fangao.lib_common.http.client.subscribers.progress.SubscriberOnNextListener;
import com.fangao.lib_common.util.NetUtil;
import com.fangao.lib_common.util.ToastUtil;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文件描述：带dialog的订阅者
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public class ProgressSubscriber<T> implements ProgressCancelListener, Observer<T> {

    private SubscriberOnNextListener<T> mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    private long delayDismissTime;
    private Disposable disposable;

    public Context getContext() {
        return context;
    }

    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context, boolean cancelable, String message, long delayDismissTime) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.delayDismissTime = delayDismissTime;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, cancelable, message);
    }

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context, String message, long delayDismissTime) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.delayDismissTime = delayDismissTime;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true, message);
    }

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context) {
        this(mSubscriberOnNextListener, context, null, 0);
    }

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context, String message) {
        this(mSubscriberOnNextListener, context, message, 0);
    }

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context, boolean cancelable, String message) {
        this(mSubscriberOnNextListener, context, cancelable, message, 0);
    }

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context, long delayDismissTime) {
        this(mSubscriberOnNextListener, context, null, delayDismissTime);
    }

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context, boolean cancelable, long delayDismissTime) {
        this(mSubscriberOnNextListener, context, cancelable, null, delayDismissTime);
    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }


    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
//            mProgressDialogHandler.sendEmptyMessage(ProgressDialogHandler.END_PROGRESS_DIALOG_LOADING);
            mProgressDialogHandler.sendEmptyMessageDelayed(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG, delayDismissTime);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        showProgressDialog();
        if (!NetUtil.INSTANCE.isConnected()) {
            if (!disposable.isDisposed()) {
                onError(new NetException("网络错误"));
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            if (mProgressDialogHandler != null) {
                mSubscriberOnNextListener.onNext(t, mProgressDialogHandler.pd);
            } else {
                mSubscriberOnNextListener.onNext(t, null);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        if (e instanceof Exception) {
            //访问获得对应的Exception
            ToastUtil.INSTANCE.toast(ExceptionHandle.handleException(e).message);
        } else {
            //将Throwable 和 未知错误的status code返回
            ToastUtil.INSTANCE.toast(ExceptionHandle.handleException(e).message);
        }
    }


    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        dismissProgressDialog();
        disposable.dispose();
    }
}