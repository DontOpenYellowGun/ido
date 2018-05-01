package com.fangao.module_login.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.HttpSubscriber;
import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.util.ToastUtil;
import com.fangao.lib_common.util.Validator;
import com.fangao.module_login.R;
import com.fangao.module_login.databinding.LoginFragmentLoginBinding;
import com.fangao.module_login.databinding.LoginFragmentRegisterBinding;
import com.fangao.module_login.model.datasouce.RemoteDataSource;
import com.fangao.module_login.viewmodel.LoginViewModel;
import com.fangao.module_login.viewmodel.RegisterViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.hawk.Hawk;

/**
 * 文件描述：登录页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/RegisterFragment")
public class RegisterFragment extends BaseFragment {

    private LoginFragmentRegisterBinding mBinding;

    private RegisterViewModel mViewModel;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_register, container, false);
        mViewModel = new RegisterViewModel(this, mBinding);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initLogo();
        initUserNameEdit();
    }

    private void initUserNameEdit() {
        final EditText viewById = _mActivity.findViewById(R.id.username_edit_text_view);
        final TextInputLayout userNameInputLayout = (TextInputLayout) _mActivity.findViewById(R.id.username_input_layout);
        viewById.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userNameInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = viewById.getText().toString();

                if (Validator.isMobile(input)) {
                    RemoteDataSource.INSTANCE.checkUserName(input)
                            .compose(RegisterFragment.this.<BaseEntity>bindToLifecycle())
                            .subscribe(new HttpSubscriber<BaseEntity>() {
                                @Override
                                protected void onSuccess(BaseEntity baseEntity) {
                                    if (baseEntity.isSuccess()) {
                                        Object result = baseEntity.getResult();
                                        JsonObject jsonObject = new Gson().fromJson(result.toString(), JsonObject.class);
                                        if (jsonObject != null) {
                                            if (jsonObject.get("exist").isJsonPrimitive()) {
                                                boolean exist = jsonObject.get("exist").getAsBoolean();
                                                if (exist) {
                                                    userNameInputLayout.setError("手机号已注册");
                                                }
                                            }
                                        }
                                    }
                                }

                                @Override
                                protected void onError(ExceptionHandle.ResponseThrowable responseThrowable) {

                                }
                            });
                }
            }
        });
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
