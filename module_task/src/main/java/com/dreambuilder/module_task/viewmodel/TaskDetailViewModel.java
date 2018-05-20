package com.dreambuilder.module_task.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.fangao.lib_common.base.BaseFragment;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.weavey.loading.lib.LoadingLayout;

import io.reactivex.functions.Action;

/**
 * 文件描述：任务详情编辑ViewModel
 * <p>
 * 作者：   Created by sven on 2018/2/10.
 */
public class TaskDetailViewModel {

    //<editor-fold desc="日志标记    Tag">
    private static final String TAG = "TaskDetailViewModel";
    //</editor-fold>

    //<editor-fold desc="基类对象    BaseFragment">
    private BaseFragment mFragment;
    //</editor-fold>

    //<editor-fold desc="数据模型    Items">
    public final ObservableList<Object> items = new ObservableArrayList<>();
    //</editor-fold>

    //<editor-fold desc="控件命令    Command">
    //<editor-fold desc="下拉刷新">
    public final ReplyCommand onRefreshCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            viewStyle.isRefreshing.set(true);
            getData();
        }
    });
    //</editor-fold>
    //<editor-fold desc="加载更多">
    public final ReplyCommand onLoadMoreCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            viewStyle.isLoadingMore.set(true);
            getData();
        }
    });
    //</editor-fold>
    //<editor-fold desc="重新加载">
    public final ReplyCommand reloadCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            viewStyle.pageState.set(LoadingLayout.Loading);
            getData();
        }
    });
    //</editor-fold>
    //<editor-fold desc="">
    public final ReplyCommand Command = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {

        }
    });
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="视图驱动    ViewStyle">
    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableField<Boolean> isRefreshing = new ObservableField<>(false);
        public final ObservableField<Boolean> isLoadingMore = new ObservableField<>(false);
        public final ObservableField<Integer> pageState = new ObservableField<>(LoadingLayout.Loading);
        public final ObservableField<String> errorMsg = new ObservableField<>();
    }
    //</editor-fold>

    //<editor-fold desc="构造方法    Construction">
    public TaskDetailViewModel(BaseFragment fragment) {
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
