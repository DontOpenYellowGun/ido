package com.fangao.module_login.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.util.Log;

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
import com.fangao.module_login.databinding.LoginFragmentForgetPasswordBinding;
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
 * 文件描述：忘记密码ViewModel
 * <p>
 * 作者：   Created by Sven on 2017/8/18.
 */

public class ForgetPwdViewModel {

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
    public ObservableField<String> mNewPwd = new ObservableField<>();//密码
    public ObservableField<String> mCountNumStr = new ObservableField<>("获取验证码");//倒计时文本
    public ObservableField<Integer> mCountNum = new ObservableField<>(60);//倒计时次数
    private Disposable countDisposable;
    //</editor-fold>

    //<editor-fold desc="控件命令    Command">
    //<editor-fold desc="提交">
    public final ReplyCommand commitCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            commit();
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
    // <editor-fold desc="其他方式找回">
    public final ReplyCommand otherMethodCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            mBaseFragment.start("");
        }
    });
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="视图风格    ViewStyle">
    private LoginFragmentForgetPasswordBinding mBinding;

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableField<Boolean> isEnableClickGetCode = new ObservableField<>(true);
    }
    //</editor-fold>

    //<editor-fold desc="构造方法    Construction">
    public ForgetPwdViewModel(BaseFragment baseFragment, LoginFragmentForgetPasswordBinding mBinding) {
        this.mBaseFragment = baseFragment;
        this.mBinding = mBinding;
    }
    //</editor-fold>

    //<editor-fold desc="接口请求    Api">
    private void commit() {

        if (mUserName.get() == null || mUserName.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请手机号或者邮箱");
            return;
        }

        if (!Validator.isEmail(mUserName.get()) && !Validator.isMobile(mUserName.get())) {
            ToastUtil.INSTANCE.toast("请输入正确的手机号或者邮箱");
            return;
        }

        if (mCode.get() == null || mCode.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请输入验证码");
            return;
        }

        if (mNewPwd.get() == null || mNewPwd.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请输入密码");
            return;
        }

        RemoteDataSource.INSTANCE
                .findPassword(
                        Validator.isMobile(mUserName.get()) ? "phone" : "email"
                        , mUserName.get()
                        , mNewPwd.get()
                        , mCode.get())
                .subscribe(new ProgressSubscriber<BaseEntity>(new SubscriberOnNextListener<BaseEntity>() {
                    @Override
                    public void onNext(BaseEntity o, LoadingDialog pd) {
                        if (o.getStatusCode() == 200) {
                            if (o.getMessage() != null) {
                                ToastUtil.INSTANCE.toast("你的密码修改成功！");
                            }
                            mBaseFragment.pop();
                        } else {
                            if (o.getDescribe() != null) {
                                ToastUtil.INSTANCE.toast(o.getDescribe());
                            }
                        }
                    }
                }, mBaseFragment.getActivity(), true, "正在修改密码..."));
    }

    @SuppressLint("CheckResult")
    private void getCode() {

        if (mUserName.get() == null || mUserName.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请手机号或者邮箱");
            return;
        }

        if (!Validator.isEmail(mUserName.get()) && !Validator.isMobile(mUserName.get())) {
            ToastUtil.INSTANCE.toast("请输入正确的手机号或者邮箱");
            return;
        }

        final Observable<BaseEntity> codeObservable = RemoteDataSource
                .INSTANCE
                .getVerifyCode(mUserName.get(), "2");

        final HttpSubscriber<BaseEntity> codeSubscriber = new HttpSubscriber<BaseEntity>() {
            @Override
            protected void onSuccess(BaseEntity o) {
                if (o.getStatusCode() == 200) {
                    ToastUtil.INSTANCE.toast("验证码已发送至您的手机");
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

//</editor-fold>
}

