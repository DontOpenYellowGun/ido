package com.fangao.module_login.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.HttpSubscriber;
import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.manager.UserManager;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.model.User;
import com.fangao.lib_common.util.ToastUtil;
import com.fangao.module_login.R;
import com.fangao.module_login.databinding.LoginFragmentSplashBinding;
import com.fangao.module_login.support.constants.Constants;
import com.fangao.module_login.model.datasouce.RemoteDataSource;
import com.orhanobut.hawk.Hawk;

import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件描述：闪屏页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/SplashFragment")
public class SplashFragment extends BaseFragment {

    private LoginFragmentSplashBinding mBinding;
    private Maybe<Long> longMaybe;
    private Disposable disposable;
    private boolean isStop = false;

    @Override
    public void onPause() {
        super.onPause();
        isStop = true;
        disposable.dispose();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isStop) {
            toNextPage();
        }
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_splash, container, false);
        mBinding.setViewModel(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toNextPage();
    }

    private void toNextPage() {
        longMaybe = Observable.timer(1000, TimeUnit.MILLISECONDS).lastElement();
        longMaybe
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        startWithPop("/login/GuideFragment");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
