package com.dreambuilder.module_task.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.dreambuilder.module_task.model.Task;
import com.fangao.lib_common.base.BaseFragment;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.weavey.loading.lib.LoadingLayout;

import io.reactivex.functions.Action;

/**
 * 文件描述：朋友圈ViewModel
 * <p>
 * 作者：   Created by sven on 2018/2/10.
 */
public class TaskListViewModel {

    //<editor-fold desc="日志标记    Tag">
    private static final String TAG = "TaskListViewModel";
    //</editor-fold>

    //<editor-fold desc="基类对象    BaseFragment">
    private BaseFragment mFragment;
    //</editor-fold>

    //<editor-fold desc="数据模型    Items">
    public final ObservableList<Task> items = new ObservableArrayList<>();
    //</editor-fold>

    //<editor-fold desc="控件命令    Command">
    //<editor-fold desc="添加任务">
    public final ReplyCommand AddTaskCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
//            mFragment.start("/task/TaskDetailFragment");
        }
    });
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="视图驱动    ViewStyle">
    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableField<Integer> pageState = new ObservableField<>(LoadingLayout.Loading);
    }
    //</editor-fold>

    //<editor-fold desc="构造方法    Construction">
    public TaskListViewModel(BaseFragment fragment) {
        this.mFragment = fragment;
        getData();
    }
    //</editor-fold>

    //<editor-fold desc="接口请求    Api">
    private void getData() {

        //        RemoteDataSource.INSTANCE.getFriendList()
        //                .compose(mFragment.<BaseEntity<List<User>>>bindToLifecycle())
        //                .subscribe(new NewHttpSubscriber<List<User>>() {
        //                    @Override
        //                    protected void onSuccess(BaseEntity<List<User>> t) {
        //                        List<User> users = t.getResult();
        //                        if (users != null) {
        //                            items.addAll(users);
        //                        }
        //                    }
        //
        //                    @Override
        //                    protected void onFail(ExceptionHandle.ResponseThrowable responseThrowable) {
        //                        ToastUtil.INSTANCE.toast(responseThrowable.message);
        //                    }
        //                });
    }
    //</editor-fold>
}
