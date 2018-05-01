package com.fangao.module_login.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.base.ToolbarFragment;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.model.InitData;
import com.fangao.module_login.R;
import com.fangao.module_login.databinding.LoginFragmentForgetPasswordBinding;
import com.fangao.module_login.viewmodel.ForgetPwdViewModel;
import com.orhanobut.hawk.Hawk;

/**
 * 文件描述：登录页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/ForgetPwdFragment")
public class ForgetPwdFragment extends ToolbarFragment {

    private LoginFragmentForgetPasswordBinding mBinding;

    private ForgetPwdViewModel mViewModel;

    @Override
    public Builder configToolbar() {
        return new Builder()
                .backgroundColor(R.color.transparent)
                .leftButtonRes(R.drawable.ic_arrow_back_grey_400_24dp);
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_forget_password, container, false);
        mViewModel = new ForgetPwdViewModel(this, mBinding);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initLogo();
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
