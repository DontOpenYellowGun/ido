package com.fangao.lib_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangao.lib_common.R;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */

public abstract class ToolbarFragment extends BaseFragment {

    private Toolbar mToolBar;

    private ToolbarFragment.Builder mBuilder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Toolbar getmToolBar() {
        return mToolBar;
    }

    public void setmToolBar(Toolbar mToolBar) {
        this.mToolBar = mToolBar;
    }

    protected void initToolbar() {
        mBuilder = configToolbar();
        mToolBar = mRootView.findViewById(R.id.toolbar);
        if (mToolBar != null) {
            mToolBar.setPadding(0, (int) getResources().getDimension(R.dimen.main_statusbar_view_height), 0, 0);
            mToolBar.setBackgroundColor(mBuilder.backgroundColor != 0 ? ContextCompat.getColor(_mActivity, mBuilder.backgroundColor) : ContextCompat.getColor(_mActivity, R.color.colorPrimary));
            mToolBar.setTitle(mBuilder.leftTitle);
            TextView mTitle = mRootView.findViewById(R.id.title_textview);
            mTitle.setText(mBuilder.title == null ? "" : mBuilder.title);
            if (mBuilder.isShowLeftButton) {
                mToolBar.setNavigationIcon(mBuilder.leftButtonRes == 0 ? R.drawable.ic_arrow_back_white_24dp : mBuilder.leftButtonRes);
                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mBuilder.leftButtonClickListener != null) {
                            mBuilder.leftButtonClickListener.onClick(view);
                        } else {
                            pop();
                        }
                    }
                });
            }
            if (mBuilder.rightMenuRes != 0) {
                mToolBar.inflateMenu(mBuilder.rightMenuRes);
                mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (mBuilder.rightMenuClickListener != null) {
                            mBuilder.rightMenuClickListener.onClick(item);
                        }
                        return false;
                    }
                });
            }
        } else {
            throw new RuntimeException("Maybe your layout not include view_toolbar");
        }
    }

    public abstract ToolbarFragment.Builder configToolbar();

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public static class Builder {

        private String title = null;
        private String leftTitle = null;
        private Boolean isShowLeftButton = true;
        private int leftButtonRes = 0;
        private int rightMenuRes = 0;
        private int backgroundColor = 0;
        private ToolbarFragment.Builder.LeftButtonClickListener leftButtonClickListener;
        private ToolbarFragment.Builder.RightMenuClickListener rightMenuClickListener;

        public Builder() {

        }

        public ToolbarFragment.Builder title(String var) {
            title = var;
            return this;
        }

        public ToolbarFragment.Builder leftTitle(String var) {
            leftTitle = var;
            return this;
        }

        public ToolbarFragment.Builder backgroundColor(int var) {
            backgroundColor = var;
            return this;
        }

        public ToolbarFragment.Builder isShowLeftButton(Boolean val) {
            isShowLeftButton = val;
            return this;
        }

        public ToolbarFragment.Builder leftButtonRes(int val) {
            leftButtonRes = val;
            return this;
        }

        public ToolbarFragment.Builder rightMenuRes(int val) {
            rightMenuRes = val;
            return this;
        }

        public ToolbarFragment.Builder leftButtonClickListener(ToolbarFragment.Builder.LeftButtonClickListener val) {
            leftButtonClickListener = val;
            return this;
        }

        public ToolbarFragment.Builder rightMenuClickListener(ToolbarFragment.Builder.RightMenuClickListener val) {
            rightMenuClickListener = val;
            return this;
        }

        public ToolbarFragment.Builder build() {
            return this;
        }

        public interface LeftButtonClickListener {
            void onClick(View view);
        }

        public interface RightMenuClickListener {
            void onClick(MenuItem item);
        }
    }


    protected void setTitle(String title) {
        TextView titleTextView = mToolBar.findViewById(R.id.title_textview);
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }
}
