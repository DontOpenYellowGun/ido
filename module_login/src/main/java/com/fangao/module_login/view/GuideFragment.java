package com.fangao.module_login.view;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.HttpSubscriber;
import com.fangao.lib_common.http.client.subscribers.exception.ExceptionHandle;
import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.manager.UserManager;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.model.User;
import com.fangao.lib_common.util.ToastUtil;
import com.fangao.module_login.model.datasouce.RemoteDataSource;
import com.kelin.mvvmlight.collectionadapter.ItemView;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.orhanobut.hawk.Hawk;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.module_login.BR;
import com.fangao.module_login.R;
import com.fangao.module_login.support.constants.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 文件描述：引导页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/GuideFragment")
public class GuideFragment extends BaseFragment {

    private ViewDataBinding mBinding;
    //倒计时次数
    public ObservableField<Integer> mCountNum = new ObservableField<>(4);

    public ObservableField<String> skipText = new ObservableField<>();

    public final ItemView mItemView = ItemView.of(BR.model, R.layout.login_item_guide_page);

    public final ObservableArrayList<String> mItems = new ObservableArrayList<>();/*new ObservableArrayList<Integer>() {{
        add(R.drawable.bg_guide_1);
        add(R.drawable.bg_guide_2);
        add(R.drawable.bg_guide_3);
        add(R.drawable.bg_guide_4);
    }};*/

    public ReplyCommand confirmCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            toNextPage();
        }
    });
    public ReplyCommand skipCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            if (mCoutDownDisposable != null && !mCoutDownDisposable.isDisposed()) {
                mCoutDownDisposable.dispose();
            }
            toNextPage();
        }
    });
    private Observable<Long> mCountDownObserable;

    private Disposable mCoutDownDisposable;

    private void toNextPage() {
        final User loginUser = UserManager.INSTANCE.getCurrentLoginUser();
        final Boolean isIntoGuidePage = Hawk.get(Constants.IS_INTO_GUIDE_PAGE, false);
        if (loginUser != null) {
            String loginUserPwd = loginUser.getPwd();
            if (loginUserPwd != null && !loginUserPwd.isEmpty() && loginUser.isAutoLogin()) {
                RemoteDataSource.INSTANCE
                        .login(loginUser.getLoginName(), loginUserPwd)
                        .compose(GuideFragment.this.<User>bindToLifecycle())
                        .subscribe(new HttpSubscriber<User>() {
                            @Override
                            protected void onSuccess(User user) {
                                UserManager.INSTANCE.updateCurrentUser(user);
                                startWithPop("/main/MainFragment");
                            }

                            @Override
                            protected void onError(ExceptionHandle.ResponseThrowable responseThrowable) {
                                ToastUtil.INSTANCE.toast(responseThrowable.message);
//                                                if (isIntoGuidePage) {
//                                                    startWithPop("/login/ChooseFragment");
//                                                } else {
//                                                    startWithPop("/login/GuideFragment");
//                                                }
                                startWithPop("/login/ChooseFragment");
                            }
                        });
            } else {
//                                if (isIntoGuidePage) {
//                                    startWithPop("/login/ChooseFragment");
//                                } else {
//                                    startWithPop("/login/GuideFragment");
//                                }
                startWithPop("/login/ChooseFragment");

            }
        } else {
//                            if (isIntoGuidePage) {
//                                startWithPop("/login/ChooseFragment");
//                            } else {
//                                startWithPop("/login/GuideFragment");
//                            }
            startWithPop("/login/ChooseFragment");
        }
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_guide, container, false);
        mBinding.setVariable(BR.viewModel, this);
        return mBinding.getRoot();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Hawk.put(Constants.IS_INTO_GUIDE_PAGE, true);
        InitData initData = (InitData) Hawk.get(HawkConstant.INIT_DATA);
        if (initData != null) {
            mItems.addAll(initData.getList());
        }
        mCountDownObserable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(mCountNum.get())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mCountNum.get() - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        skipText.set(mCountNum + "s 跳过");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        mCountDownObserable
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        skipText.set(aLong + "s 跳过");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        toNextPage();
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mCoutDownDisposable = disposable;
                    }
                });

    }

    @Override
    public boolean onBackPressedSupport() {
        getActivity().finish();
        return super.onBackPressedSupport();
    }
}
