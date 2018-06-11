package com.dreambuilder.module_task.view;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dreambuilder.module_task.R;
import com.dreambuilder.module_task.databinding.TaskFragmentTaskListBinding;
import com.dreambuilder.module_task.model.Task;
import com.dreambuilder.module_task.model.TaskType;
import com.dreambuilder.module_task.view.adapter.TaskListAdapter;
import com.dreambuilder.module_task.view.adapter.TaskTypeAdapter;
import com.dreambuilder.module_task.view.anim.SpecialFadeAnimator;
import com.dreambuilder.module_task.viewmodel.TaskListViewModel;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.util.DensityUtils;
import com.fangao.lib_common.util.ToastUtil;
import com.fangao.lib_common.view.widget.GridSpacingItemDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


/**
 * 文件描述：主页
 *
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
@Route(path = "/task/TaskListFragment")
public class TaskListFragment extends BaseFragment {

    private TaskFragmentTaskListBinding mBinding;

    private TaskListViewModel mViewModel;

    private TaskListAdapter mTaskListAdapter;

    private ObservableList.OnListChangedCallback<ObservableList<Task>> listChangedCallback;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.task_fragment_task_list, container, false);
        mViewModel = new TaskListViewModel(this);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAddButton();
        setupRecyclerView();
        setupTypeRecyclerView();
    }

    private void setupAddButton() {
        mBinding.addTaskImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentAnimator(new SpecialFadeAnimator());
                start("/task/TaskDetailFragment");
            }
        });
    }

    private void setupTypeRecyclerView() {
        mBinding.typeRecyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        mBinding.typeRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, DensityUtils.dip2px(16), false, 0));
        TaskTypeAdapter adapter = new TaskTypeAdapter(_mActivity, mViewModel.mTaskType);
        adapter.setOnItemClickListener(new TaskTypeAdapter.onItemClickListener() {
            @Override
            public void onClick(View holder, int position) {
                ToastUtil.INSTANCE.toast(String.valueOf(position));
            }
        });
        mBinding.typeRecyclerView.setAdapter(adapter);
        mViewModel.mTaskType.add(new TaskType());
        mViewModel.mTaskType.add(new TaskType());
    }

    @Override
    public void onDestroy() {
        mViewModel.items.removeOnListChangedCallback(listChangedCallback);
        super.onDestroy();
    }

    private void setupRecyclerView() {
        RecyclerView taskRecyclerView = mBinding.taskRecyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        taskRecyclerView.setLayoutManager(linearLayoutManager);
        taskRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(_mActivity)
                        .color(ContextCompat.getColor(_mActivity, R.color.line_d6d6d6))
                        .size(1)
                        .build());
        mTaskListAdapter = new TaskListAdapter(R.layout.task_item_task_list, mViewModel.items);
        mTaskListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.INSTANCE.toast(String.valueOf(position));
            }
        });
        mTaskListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//                mViewModel.items.remove(position);
                return false;
            }
        });
        taskRecyclerView.setAdapter(mTaskListAdapter);
        listChangedCallback = new ObservableList.OnListChangedCallback<ObservableList<Task>>() {
            @Override
            public void onChanged(ObservableList<Task> sender) {
                mTaskListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<Task> sender, int positionStart, int itemCount) {
                mTaskListAdapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Task> sender, int positionStart, int itemCount) {
                mTaskListAdapter.notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<Task> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<Task> sender, int positionStart, int itemCount) {
                mTaskListAdapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        };
        mViewModel.items.addOnListChangedCallback(listChangedCallback);
        for (int i = 0; i < 4; i++) {
            mViewModel.items.add(new Task("todo" + i));
        }
    }
}
