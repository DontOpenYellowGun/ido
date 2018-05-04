package com.dreambuilder.module_task.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dreambuilder.module_main.R;
import com.dreambuilder.module_main.databinding.TaskFragmentTaskListBinding;
import com.fangao.lib_common.base.BaseFragment;


/**
 * 文件描述：主页
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
@Route(path = "/task/TaskListFragment")
public class TaskListFragment extends BaseFragment {

    private TaskFragmentTaskListBinding mBinding;

    private MaterialDialog mQuitDialog;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.task_fragment_task_list, container, false);
        mBinding.setViewModel(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
