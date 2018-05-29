package com.dreambuilder.module_task.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreambuilder.module_task.BR;
import com.dreambuilder.module_task.R;
import com.dreambuilder.module_task.model.TaskType;

public class TaskTypeAdapter extends RecyclerView.Adapter<TaskTypeAdapter.MyViewHolder> {

    private final Context mContext;

    private ObservableList<TaskType> mDatas;

    private static final int DEFAULT = 0;

    private static final int ADD = 1;

    public TaskTypeAdapter(Context context, ObservableList<TaskType> datas) {
        this.mDatas = datas;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == mDatas.size()) {
            type = ADD;
        } else {
            type = DEFAULT;
        }
        return type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId;
        switch (viewType) {
            case ADD:
                layoutId = R.layout.task_item_task_add;
                break;
            case DEFAULT:
            default:
                layoutId = R.layout.task_item_task_type;
                break;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding itemBinding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        switch (getItemViewType(position)) {
            case ADD:
                break;
            case DEFAULT:
            default:
                holder.bind(mDatas.get(position));
                break;
        }

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(holder.binding.getRoot(), position);
                }
            }
        });
        holder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onClick(holder.binding.getRoot(), position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.item, obj);
            binding.executePendingBindings();
        }
    }

    private onItemLongClickListener onItemLongClickListener;

    public interface onItemLongClickListener {
        void onClick(View holder, int position);
    }

    public onItemLongClickListener getonItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setonItemLongClickListener(onItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onClick(View holder, int position);
    }

    public onItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
