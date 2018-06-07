package com.dreambuilder.module_task.view;


import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dreambuilder.module_task.R;
import com.dreambuilder.module_task.databinding.TaskFragmentTaskDetailBinding;
import com.dreambuilder.module_task.viewmodel.TaskDetailViewModel;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.util.DensityUtils;

@Route(path = "/task/TaskDetailFragment")
public class TaskDetailFragment extends BaseFragment {

    private TaskDetailViewModel mViewModel;

    private TaskFragmentTaskDetailBinding mBinding;

    private Animator.AnimatorListener mRevealAnimListener;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRootView();
    }

    private void initRootView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mRootView.post(new Runnable() {
                @Override
                public void run() {
                    Animator animator = createRevealAnimator(false, 0, DensityUtils.getScreenHeight(_mActivity));
                    animator.start();
                }
            });
        } else {

        }
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new TaskDetailViewModel(this);
        this.mBinding = DataBindingUtil.inflate(inflater, R.layout.task_fragment_task_detail, container, false);
        this.mBinding.setViewModel(mViewModel);
        return this.mBinding.getRoot();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Animator createRevealAnimator(boolean reversed, int x, int y) {
        float hypot = (float) Math.hypot(DensityUtils.getScreenWidth(_mActivity), DensityUtils.getScreenHeight(_mActivity));
        float startRadius = reversed ? hypot : 0;
        float endRadius = reversed ? 0 : hypot;

        Animator animator;
        animator = ViewAnimationUtils.createCircularReveal(
                mRootView, x, y,
                startRadius,
                endRadius);
        animator.setDuration(600);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        mRevealAnimListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                int startColor = ContextCompat.getColor(_mActivity, R.color.colorPrimary);
                int endColor = ContextCompat.getColor(_mActivity, R.color.windowBackground);
                ValueAnimator colorAnim = ObjectAnimator.ofInt(mBinding.getRoot(), "backgroundColor", startColor, endColor);
                colorAnim.setDuration(800);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatMode(ValueAnimator.RESTART);
                colorAnim.setRepeatCount(0);
                colorAnim.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("TaskDetailFragment", "onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d("TaskDetailFragment", "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d("TaskDetailFragment", "onAnimationRepeat");
            }
        };
        animator.addListener(mRevealAnimListener);
        return animator;
    }

}
