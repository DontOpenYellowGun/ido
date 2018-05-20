package com.fangao.lib_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fangao.lib_common.R;

import me.yokeyword.fragmentation.anim.DefaultFadeAnimator;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/10/21.
 */

public abstract class BaseFragment extends EventFragment {

    protected View mRootView;

    private int mWindowBackgroundColor = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = initBinding(inflater, container, savedInstanceState);
        if (getWindowBackgroundColor() == 0) {
            mRootView.setBackgroundColor(ContextCompat.getColor(BaseApplication.getInstance(), R.color.windowBackground));
        } else {
            mRootView.setBackgroundColor(ContextCompat.getColor(BaseApplication.getInstance(), getWindowBackgroundColor()));
        }
        return mRootView;
    }

    public int getWindowBackgroundColor() {
        return mWindowBackgroundColor;
    }

    public void setWindowBackgroundColor(int mWindowBackgroundColor) {
        this.mWindowBackgroundColor = mWindowBackgroundColor;
        mRootView.setBackgroundColor(getWindowBackgroundColor());
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
//        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    protected abstract View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultFadeAnimator();
    }
}
