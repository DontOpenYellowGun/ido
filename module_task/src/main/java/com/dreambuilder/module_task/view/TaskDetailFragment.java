package com.dreambuilder.module_task.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dreambuilder.module_main.R;
import com.dreambuilder.module_main.databinding.TaskFragmentTaskDetailBinding;
import com.dreambuilder.module_task.viewmodel.TaskDetailViewModel;
import com.fangao.lib_common.base.BaseFragment;

@Route(path = "/task/TaskDetailFragment")
public class TaskDetailFragment extends BaseFragment {

    private TaskDetailViewModel mViewModel;

    private TaskFragmentTaskDetailBinding mBinding;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = new TaskDetailViewModel(this);
        this.mBinding = DataBindingUtil.inflate(inflater, R.layout.task_fragment_task_detail, container, false);
        this.mBinding.setViewModel(mViewModel);
        return this.mBinding.getRoot();
    }

    private void initView() {

    }

}
