package com.dreambuilder.module_task.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dreambuilder.module_main.R;
import com.dreambuilder.module_task.model.Task;

import java.util.List;

public class TaskListAdapter extends BaseQuickAdapter<Task, BaseViewHolder> {

    public TaskListAdapter(int layoutResId, @Nullable List<Task> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Task item) {
        helper.setText(R.id.name, item.getName());
    }
}
