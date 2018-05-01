package com.dreambuilder.module_main.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fangao.lib_common.base.BaseFragment;
import com.fangao.lib_common.event.CommonEvent;
import com.fangao.lib_common.view.widget.BottomBar;
import com.fangao.lib_common.view.widget.BottomBarTab;
import com.fangao.module_main.R;
import com.fangao.module_main.databinding.MainFragmentMainBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * 文件描述：主页
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
@Route(path = "/main/MainFragment")
public class MainFragment extends BaseFragment {

    private static final long WAIT_TIME = 2000L;

    private long TOUCH_TIME = 0;

    private MainFragmentMainBinding mBinding;

    private MaterialDialog mQuitDialog;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment_main, container, false);
        mBinding.setViewModel(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateChildFragments();
        inflateBottomBar();
        initQuitDialog();
    }

    private void initQuitDialog() {
        mQuitDialog = new MaterialDialog.Builder(_mActivity)
                .title("提示")
                .content("确定退出客户端？")
                .positiveText("确定")
                .negativeText("取消")
                .canceledOnTouchOutside(true)
                .cancelable(true)
                .autoDismiss(true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        goHome();
                    }
                })
                .build();
    }

    private void goHome() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        _mActivity.startActivity(home);
    }

    private void inflateBottomBar() {
        mBinding.bottomBar.setCurrentItem(0);
        mBinding.bottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_message_black_24dp, "爱体消息"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_message_black_24dp, "联系人"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_message_black_24dp, "发现"))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_message_black_24dp, "个人中心"));
        mBinding.bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                mBinding.viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void inflateChildFragments() {

        SupportFragment messageFragment = (SupportFragment) ARouter.getInstance().build("/main/ConversationListParentFragment").navigation();
        SupportFragment contactsFragment = (SupportFragment) ARouter.getInstance().build("/main/ContactsFragment").navigation();
        SupportFragment discoverFragment = (SupportFragment) ARouter.getInstance().build("/main/DiscoverFragment").navigation();
        Bundle args = new Bundle();
        args.putString("url", "discover/index/1");
        args.putBoolean("isShowToolbar", false);
        discoverFragment.setArguments(args);
        SupportFragment meFragment = (SupportFragment) ARouter.getInstance().build("/main/MeFragment").navigation();

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(messageFragment);
        fragments.add(contactsFragment);
        fragments.add(discoverFragment);
        fragments.add(meFragment);

        mBinding.viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mBinding.viewPager.setOffscreenPageLimit(4);
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBinding.bottomBar.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBinding.viewPager.setCanScroll(false);
    }

    @Override
    public boolean onBackPressedSupport() {
        goHome();
        return true;
    }

    @Override
    public Boolean getUseEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ç(CommonEvent event) {
        Log.d("√", "onReceiveEvent() called with: event = [" + event + "]");
    }
}
