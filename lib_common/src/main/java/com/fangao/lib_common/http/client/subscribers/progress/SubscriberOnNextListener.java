package com.fangao.lib_common.http.client.subscribers.progress;

import com.weavey.loading.lib.LoadingDialog;


/**
 * Created by liukun on 16/3/10.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t, LoadingDialog pd);
}
