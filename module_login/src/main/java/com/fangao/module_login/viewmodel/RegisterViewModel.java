package com.fangao.module_login.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;

import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.HttpSubscriber;
import com.fangao.lib_common.http.client.subscribers.ProgressSubscriber;
import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.http.client.subscribers.progress.SubscriberOnNextListener;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.util.ToastUtil;
import com.fangao.lib_common.util.Validator;
import com.fangao.module_login.databinding.LoginFragmentRegisterBinding;
import com.fangao.module_login.model.datasouce.RemoteDataSource;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.orhanobut.hawk.Hawk;
import com.weavey.loading.lib.LoadingDialog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件描述：登录ViewModel
 * <p>
 * 作者：   Created by Sven on 2017/8/18.
 */

public class RegisterViewModel {

    //<editor-fold desc="日志标记    Tag">
    private static final String TAG = "ForgetPwdViewModel";
    //</editor-fold>

    //<editor-fold desc="基类对象    BaseActivity">
    private BaseFragment mBaseFragment;
    //</editor-fold>

    //<editor-fold desc="数据模型    Items">
    public ObservableField<String> mTitle = new ObservableField<>(((InitData) Hawk.get(HawkConstant.INIT_DATA)).getLoginImageUrl());//密码
    public ObservableField<String> mUserName = new ObservableField<>();//用户名
    public ObservableField<String> mCode = new ObservableField<>();//验证码
    public ObservableField<String> mPassword = new ObservableField<>();//密码
    public ObservableField<String> mCountNumStr = new ObservableField<>("获取验证码");//倒计时文本
    public ObservableField<Integer> mCountNum = new ObservableField<>(60);//倒计时次数
    private Disposable countDisposable;
    //</editor-fold>

    //<editor-fold desc="控件命令    Command">
    //<editor-fold desc="注册">
    public final ReplyCommand registerCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            register();
        }
    });
    //</editor-fold>
    // <editor-fold desc="获取验证码">
    public final ReplyCommand getCodeCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            getCode();
        }
    });


    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="视图风格    ViewStyle">
    private LoginFragmentRegisterBinding mBinding;

    public final RegisterViewModel.ViewStyle viewStyle = new RegisterViewModel.ViewStyle();

    public class ViewStyle {
        public final ObservableField<Boolean> isEnableClickGetCode = new ObservableField<>(true);
    }
    //</editor-fold>

    //<editor-fold desc="构造方法    Construction">
    public RegisterViewModel(BaseFragment baseFragment, LoginFragmentRegisterBinding mBinding) {
        this.mBaseFragment = baseFragment;
        this.mBinding = mBinding;
    }
    //</editor-fold>

    //<editor-fold desc="接口请求    Api">
    private void register() {

        if (mUserName.get() == null || mUserName.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请手机号");
            return;
        }

        if (!Validator.isMobile(mUserName.get())) {
            ToastUtil.INSTANCE.toast("请输入正确的手机号");
            return;
        }

        if (mCode.get() == null || mCode.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请输入验证码");
            return;
        }

        if (mPassword.get() == null || mPassword.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请输入密码");
            return;
        }

        try {

//            RemoteDataSource.INSTANCE
//                    .checkCode(mUserName.get(), mCode.get(), "1")
//                    .flatMap(new Function<Integer, ObservableSource<Object>>() {
//                        @Override
//                        public ObservableSource<Object> apply(Integer integer) throws Exception {
//                            if (integer == 200) {
//                                return RemoteDataSource.INSTANCE.register(mUserName.get(), mPassword.get(), mCode.get());
//                            } else {
//                                ToastUtil.INSTANCE.toast("验证码错误");
//                                return null;
//                            }
//                        }
//                    })
//                    .subscribe(new HttpSubscriber<Object>() {
//                        @Override
//                        protected void onSuccess(Object o) {
//                            Log.d(TAG, "onSuccess() called with: o = [" + o + "]");
//                        }
//
//                        @Override
//                        protected void onFail(ApiHandle.ResponseThrowable responseThrowable) {
//                            Log.d(TAG, "onFail() called with: responseThrowable = [" + responseThrowable + "]");
//                        }
//                    });
            RemoteDataSource.INSTANCE
                    .register(mUserName.get(), mPassword.get(), mCode.get())
                    .compose(mBaseFragment.<BaseEntity>bindToLifecycle())
                    .subscribe(new ProgressSubscriber<>(new SubscriberOnNextListener<BaseEntity>() {
                        @Override
                        public void onNext(BaseEntity abs, LoadingDialog pd) {
                            if (abs.getStatusCode() == 200) {
                                ToastUtil.INSTANCE.toast("注册成功");
                                mBaseFragment.pop();
                            } else {
                                ToastUtil.INSTANCE.toast(abs.getDescribe());
                            }
                        }
                    }, mBaseFragment.getActivity(), true, "注册中...", 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("CheckResult")
    private void getCode() {

        if (mUserName.get() == null || mUserName.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请输入手机号");
            return;
        }

        if (!Validator.isMobile(mUserName.get())) {
            ToastUtil.INSTANCE.toast("请输入正确的手机号");
            return;
        }

        final Observable<BaseEntity> codeObservable = RemoteDataSource
                .INSTANCE
                .getVerifyCode(mUserName.get(), "1");

        final HttpSubscriber<BaseEntity> codeSubscriber = new HttpSubscriber<BaseEntity>() {
            @Override
            protected void onSuccess(BaseEntity o) {
                if (o.getStatusCode() == 200) {
                    ToastUtil.INSTANCE.toast("验证码已发送至您的手机！");
                } else {
                    ToastUtil.INSTANCE.toast(o.getDescribe());
                }
            }

            @Override
            protected void onError(ExceptionHandle.ResponseThrowable responseThrowable) {
                viewStyle.isEnableClickGetCode.set(true);
                mCountNumStr.set("重新获取");
                countDisposable.dispose();
            }
        };

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(mCountNum.get() + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mCountNum.get() - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        countDisposable = disposable;
                        viewStyle.isEnableClickGetCode.set(false);
                        codeObservable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .compose(mBaseFragment.<BaseEntity>bindToLifecycle())
                                .subscribe(codeSubscriber);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mCountNumStr.set(String.valueOf(aLong));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        viewStyle.isEnableClickGetCode.set(true);
                        mCountNumStr.set("重新获取");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        viewStyle.isEnableClickGetCode.set(true);
                        mCountNumStr.set("重新获取");
                    }
                });
    }
//</editor-fold>}

}

