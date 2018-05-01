package com.fangao.module_login.view;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.model.InitData;
import com.fangao.module_login.R;
import com.fangao.module_login.databinding.LoginFragmentChooseBinding;
import com.fangao.module_login.databinding.LoginFragmentLoginBinding;
import com.fangao.module_login.viewmodel.LoginViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.orhanobut.hawk.Hawk;

import io.reactivex.functions.Action;

/**
 * 文件描述：登录页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/ChooseFragment")
public class ChooseFragment extends BaseFragment {

    private LoginFragmentChooseBinding mBinding;

    public ObservableField<String> mTitle = new ObservableField<>(((InitData) Hawk.get(HawkConstant.INIT_DATA)).getLoginImageUrl());//密码


    public ReplyCommand mLoginCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            start("/login/LoginFragment");
        }
    });

    public ReplyCommand mRegisterCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            start("/login/RegisterFragment");
        }
    });

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_choose, container, false);
        mBinding.setViewModel(this);
        return mBinding.getRoot();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initLogo();
    }

    @Override
    public boolean onBackPressedSupport() {
        getActivity().finish();
        return super.onBackPressedSupport();
    }

    private void initLogo() {
        InitData initData = (InitData) Hawk.get(HawkConstant.INIT_DATA);
        if (initData != null) {
            String loginImageUrl = initData.getLoginImageUrl();
            Glide.with(this)
                    .load(loginImageUrl)
                    .into(((ImageView) mBinding.getRoot().findViewById(R.id.icon)));
        }
    }


    @Override
    public int getWindowBackgroundColor() {
        return R.color.windowBackground;
    }
}
