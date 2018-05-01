package com.fangao.module_login.view;

import android.Manifest;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fangao.lib_common.base.BaseActivity;
import com.fangao.lib_common.base.BaseApplication;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.NewHttpSubscriber;
import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.util.AppInnerDownLoder;
import com.fangao.lib_common.util.DeviceUtils;
import com.fangao.lib_common.util.FileDownloadManager;
import com.fangao.lib_common.util.ToastUtil;
import com.fangao.module_login.R;
import com.fangao.module_login.model.datasouce.RemoteDataSource;
import com.orhanobut.hawk.Hawk;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 文件描述：启动Activity
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
@Route(path = "/login/LauncherActivity")
public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected View initBinding() {
        return DataBindingUtil.setContentView(LauncherActivity.this, R.layout.login_activity_launcher).getRoot();
    }

    private void initView() {
        String tag = getIntent().getStringExtra("tag");
        if (tag != null) {
            switch (tag) {
                default:
                case "reLogin":
                    SupportFragment navigation = (SupportFragment) ARouter.getInstance().build("/login/LoginFragment").navigation();
                    loadRootFragment(com.fangao.lib_common.R.id.fragment_container, navigation);
                    break;
            }
        } else {
            loadRootFragment(com.fangao.lib_common.R.id.fragment_container, new SplashFragment());
        }
    }

    private void initData() {
        new RxPermissions(this)
                .request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            RemoteDataSource
                                    .INSTANCE
                                    .init()
                                    .subscribe(new NewHttpSubscriber<InitData>() {
                                        @Override
                                        protected void onSuccess(BaseEntity<InitData> t) {
                                            InitData initData = t.getResult();
                                            if (t.getStatusCode() == 200 && initData != null) {
                                                Hawk.put(HawkConstant.INIT_DATA, initData);
                                                Hawk.put(HawkConstant.TOKEN, initData.getToken());
                                                Hawk.put(HawkConstant.PUBLIC_KEY, initData.getPublicKey());
                                                Hawk.put(HawkConstant.APP_SECRET, initData.getSecret());
                                                showUpdateDialog(initData);
                                            }

                                        }

                                        @Override
                                        protected void onFail(ExceptionHandle.ResponseThrowable responseThrowable) {

                                        }
                                    });
                        } else {
                            ToastUtil.INSTANCE.toast("请查看App相关权限");
                        }
                    }
                });
    }

    private void showUpdateDialog(InitData initData) {
        final String apkUrl = initData.getUrl();
        boolean forceUpgrade = initData.isForceUpgrade();
//        final String apkUrl = "https://wildeyess.oss-cn-beijing.aliyuncs.com/app-debug.apk";
        if (initData.isHasNewVersion()) {
            if (forceUpgrade) {
                forceUpdate(initData, apkUrl);
            } else {
                normalUpdate(initData, apkUrl);
            }
        }
    }

    private void normalUpdate(final InitData initData, final String apkUrl) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("发现新版本" + " (" + initData.getNewVersion() + ") ")
                .content(
                        "当前版本:(" + DeviceUtils.getVersionName(BaseApplication.getInstance()) + ")" + "\n" +
                                initData.getContent())
                .positiveText("立即升级")
                .negativeText("暂不升级")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        FileDownloadManager.getInstance(LauncherActivity.this).startDownload(apkUrl, "下载新版本", "", "LoveBody");
                    }
                })
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        String newVersion = initData.getNewVersion();
                        List<String> versions = Hawk.get(HawkConstant.VERSION);
                        if (versions == null) {
                            versions = new ArrayList<>();
                        }
                        versions.add(newVersion);
                        Hawk.put(HawkConstant.VERSION, versions);
                    }
                })
                .autoDismiss(true)
                .cancelable(true)
                .canceledOnTouchOutside(true)
                .build();
        List<String> versions = Hawk.get(HawkConstant.VERSION);
        if (versions == null || !versions.contains(initData.getNewVersion())) {
            dialog.show();
        }
    }

    private void forceUpdate(InitData initData, final String apkUrl) {
        new MaterialDialog.Builder(this)
                .title("发现新版本" + " (" + initData.getNewVersion() + ") ")
                .content(
                        "当前版本:(" + DeviceUtils.getVersionName(BaseApplication.getInstance()) + ")" + "\n" +
                                initData.getContent())
                .positiveText("立即升级")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        AppInnerDownLoder.downLoadApk(LauncherActivity.this, apkUrl, "LoveBody");
                    }
                })
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }
}
