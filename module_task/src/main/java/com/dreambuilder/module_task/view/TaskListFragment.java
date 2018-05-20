package com.dreambuilder.module_task.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dreambuilder.module_main.R;
import com.dreambuilder.module_main.databinding.TaskFragmentTaskListBinding;
import com.dreambuilder.module_task.viewmodel.TaskListViewModel;
import com.fangao.lib_common.base.BaseFragment;


/**
 * 文件描述：主页
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
@Route(path = "/task/TaskListFragment")
public class TaskListFragment extends BaseFragment {

    private TaskFragmentTaskListBinding mBinding;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.task_fragment_task_list, container, false);
        TaskListViewModel mViewModel = new TaskListViewModel(this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.addTaskImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start("/task/TaskDetailFragment");
            }
        });
        RecyclerView taskRecyclerView = mBinding.taskRecyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        taskRecyclerView.setLayoutManager(linearLayoutManager);
        
    }
}
