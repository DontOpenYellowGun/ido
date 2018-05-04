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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.model.InitData;
import com.fangao.module_login.R;
import com.fangao.module_login.databinding.LoginFragmentLoginBinding;
import com.fangao.module_login.view.widget.SlideFromTopPopup;
import com.fangao.module_login.viewmodel.LoginViewModel;
import com.orhanobut.hawk.Hawk;

/**
 * 文件描述：登录页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/LoginFragment")
public class LoginFragment extends BaseFragment {

    private LoginFragmentLoginBinding mBinding;

    private LoginViewModel mViewModel;

    private SlideFromTopPopup mSlideFromTopPopup;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_login, container, false);
        return mBinding.getRoot();
    }

    public ObservableField<String> mTitle = new ObservableField<>();//密码

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        mViewModel = new LoginViewModel(this, mBinding);
        mBinding.setViewModel(mViewModel);
//        initPopView();
        initLogo();
    }

    private void initLogo() {
        InitData initData = (InitData) Hawk.get(HawkConstant.INIT_DATA);
        if (initData != null) {
            String loginImageUrl = initData.getLoginImageUrl();
            Glide.with(this)
                    .load(loginImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((ImageView) mBinding.getRoot().findViewById(R.id.icon)));
        }
    }

//    private void initPopView() {
//        final List<User> users = UserManager.INSTANCE.getLoginUsers();
//        Observable.fromIterable(users)
//                .toSortedList(new Comparator<User>() {
//                    @TargetApi(Build.VERSION_CODES.KITKAT)
//                    @Override
//                    public int compare(User user, User t1) {
//                        return Long.compare(t1.getLoginTime(), user.getLoginTime());
//                    }
//                })
//                .subscribe(new Consumer<List<User>>() {
//                    @Override
//                    public void accept(final List<User> users) throws Exception {
//                        mSlideFromTopPopup = new SlideFromTopPopup(_mActivity, users);
//                        mSlideFromTopPopup.setNeedPopupFade(false);
//                        mSlideFromTopPopup.setDismissWhenTouchOutside(true);
//                        mSlideFromTopPopup.setBlurBackgroundEnable(false);
//                        mSlideFromTopPopup.setOnChildClickListener(new SlideFromTopPopup.OnChildClickListener() {
//                            @Override
//                            public void onClick(View view, int position) {
//                                if (view.getId() == R.id.right_icon) {
//                                    if (users.get(position).getId().toString().equalsIgnoreCase(UserManager.INSTANCE.getCurrentUser().getId().toString())) {
//                                        UserManager.INSTANCE.deleteLoginUser(users.get(position));
//                                        users.remove(position);
//                                        ((BaseAdapter) mSlideFromTopPopup.getAdapter()).notifyDataSetChanged();
//                                        if (users.size() > 0) {
//                                            UserManager.INSTANCE.setCurrentUser(users.get(position));
//                                            User user = users.get(0);
//                                            mViewModel.replaceAccount.execute(user);
//                                            mBinding.usernameEdit.setText(user.getLoginName());
//
//                                            if (user.isRememberPwd()) {
//                                                mBinding.passwordEdit.setText(user.getPwd());
//                                            } else {
//                                                mBinding.passwordEdit.setText("");
//                                            }
//                                            mViewModel.mIsSavePwd.set(user.isRememberPwd());
//                                        } else {
//                                            UserManager.INSTANCE.setCurrentUser(null);
//                                            mSlideFromTopPopup.dismiss();
//                                            mViewModel.replaceAccount.execute(null);
//                                            mBinding.usernameEdit.setText("");
//                                            mBinding.passwordEdit.setText("");
//                                        }
//                                    } else {
//                                        UserManager.INSTANCE.deleteLoginUser(users.get(position));
//                                        users.remove(position);
//                                        ((BaseAdapter) mSlideFromTopPopup.getAdapter()).notifyDataSetChanged();
//                                    }
//                                }
//                            }
//                        });
//                        mSlideFromTopPopup.setmOnItemClickListener(new SlideFromTopPopup.onItemClickListener() {
//                            @Override
//                            public void onClick(View view, int position) {
//                                mSlideFromTopPopup.dismiss();
//                                mViewModel.replaceAccount.execute(users.get(position));
//                            }
//                        });
//                        mSlideFromTopPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
//                            @Override
//                            public void onDismiss() {
//
//                            }
//                        });
//                        mBinding.moreAccount.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (mSlideFromTopPopup.isShowing()) {
//                                    mSlideFromTopPopup.dismiss();
//                                } else {
//                                    if (users.size() > 0) {
//                                        mSlideFromTopPopup.showPopupWindow(mBinding.usernameLayout);
//                                    }
//                                }
//                            }
//                        });
//                    }
//                });
//    }

    @Override
    public int getWindowBackgroundColor() {
        return R.color.windowBackground;
    }
}
