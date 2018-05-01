package com.fangao.module_login.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.ProgressSubscriber;
import com.fangao.lib_common.http.client.subscribers.progress.SubscriberOnNextListener;
import com.fangao.lib_common.manager.UserManager;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.model.User;
import com.fangao.lib_common.model.datasource.LocalDataSource;
import com.fangao.lib_common.util.ToastUtil;
import com.fangao.module_login.R;
import com.fangao.module_login.databinding.LoginFragmentLoginBinding;
import com.fangao.module_login.model.datasouce.RemoteDataSource;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.orhanobut.hawk.Hawk;
import com.weavey.loading.lib.LoadingDialog;

import java.util.HashMap;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 文件描述：登录ViewModel
 * <p>
 * 作者：   Created by Sven on 2017/8/18.
 */

public class LoginViewModel {

    //<editor-fold desc="日志标记    Tag">
    private static final String TAG = "LoginViewModel";
    //</editor-fold>

    //<editor-fold desc="基类对象    BaseActivity">
    private BaseFragment mBaseFragment;
    //</editor-fold>

    //<editor-fold desc="数据模型    Items">
    public ObservableField<String> mUserName = new ObservableField<>();//用户名
    public ObservableField<String> mPassword = new ObservableField<>();//密码
    public ObservableBoolean mIsSavePwd = new ObservableBoolean(true);
    //</editor-fold>

    //<editor-fold desc="控件命令    Command">
    //<editor-fold desc="登录">
    public final ReplyCommand loginCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            login();
        }
    });
    //</editor-fold>
    //<editor-fold desc="忘记密码">
    public final ReplyCommand forgetPwdCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            mBaseFragment.start("/login/ForgetPwdFragment");
        }
    });
    //</editor-fold>//<editor-fold desc="忘记密码">
    public final ReplyCommand registCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            mBaseFragment.start("/login/RegisterFragment");
        }
    });
    //</editor-fold>
    //<editor-fold desc="替换用户">
    public final ReplyCommand<User> replaceAccount = new ReplyCommand<>(new Consumer<User>() {
        @Override
        public void accept(User user) throws Exception {
            if (user != null) {
                UserManager.INSTANCE.setCurrentUser(user);
                mUserName.set(user.getLoginName());
                if (user.isRememberPwd()) {
                    mPassword.set(user.getPwd());
                } else {
                    mPassword.set("");
                }
                mIsSavePwd.set(user.isRememberPwd());
            } else {
                UserManager.INSTANCE.setCurrentUser(null);
                mUserName.set("");
                mPassword.set("");
            }
        }
    });
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="视图风格    ViewStyle">
    private LoginFragmentLoginBinding mBinding;

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {

    }
    //</editor-fold>

    //<editor-fold desc="构造方法    Construction">
    public LoginViewModel(BaseFragment baseFragment, LoginFragmentLoginBinding mBinding) {
        this.mBaseFragment = baseFragment;
        this.mBinding = mBinding;
        initData();
    }

    private void initData() {
        HashMap<String, User> currentUsers = Hawk.get(HawkConstant.LOGIN_USERS);
        if (currentUsers != null) {
            String loginUserId = String.valueOf(Hawk.get(HawkConstant.LOGIN_USER_ID));
            if (loginUserId != null) {
                User currentUser = currentUsers.get(loginUserId);
                if (currentUser != null) {
                    mUserName.set(currentUser.getLoginName());
                    if (currentUser.isRememberPwd()) {
                        mPassword.set(currentUser.getPwd());
                    }
                    mIsSavePwd.set(currentUser.isRememberPwd());
                }
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="接口请求    Api">
    private void login() {

        if (mUserName.get() == null || mUserName.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请输入用户名");
            return;
        }

        if (mPassword.get() == null || mPassword.get().isEmpty()) {
            ToastUtil.INSTANCE.toast("请输入用密码");
            return;
        }

        RemoteDataSource.INSTANCE
                .login(mUserName.get(), mPassword.get())
                .compose(mBaseFragment.<User>bindToLifecycle())
                .subscribe(new ProgressSubscriber<>(new SubscriberOnNextListener<User>() {
                    @Override
                    public void onNext(User user, LoadingDialog pd) {

                        user.setPwd(mPassword.get());
                        user.setAutoLogin(true);
                        user.setLoginName(mUserName.get());
                        user.setLoginTime(System.currentTimeMillis());
                        user.setRememberPwd(mIsSavePwd.get());
                        UserManager.INSTANCE.setCurrentUser(user);
                        UserManager.INSTANCE.addLoginUser(user);

                        if (mBaseFragment.getActivity() != null) {
                            for (int i = 0; i < mBaseFragment.getActivity().getSupportFragmentManager().getBackStackEntryCount(); i++) {
                                mBaseFragment.getActivity().getSupportFragmentManager().popBackStack();
                            }
                            SupportFragment navigation = (SupportFragment) ARouter.getInstance().build("/main/MainFragment").navigation();
                            ((SupportActivity) mBaseFragment.getActivity()).loadRootFragment(R.id.fragment_container, navigation, true, true);
                        }
                    }
                }, mBaseFragment.getActivity(), true, "登陆中...", 0));
    }
    //</editor-fold>
}

