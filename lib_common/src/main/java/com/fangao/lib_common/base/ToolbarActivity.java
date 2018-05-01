package com.fangao.lib_common.base;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fangao.lib_common.R;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/10/21.
 */

public abstract class ToolbarActivity extends BaseActivity {

    private static Menu menu;

    private Toolbar mToolBar;

    private Builder mBuilder;

    private String centerTitle = null;

    private String leftTitle = null;

    private Boolean isShowLeftButton = true;

    private int leftButtonRes = 0;

    private int rightMenuRes = 0;

    private int backgroundColor = 0;

    private ToolbarActivity.Builder.LeftButtonClickListener leftButtonClickListener;

    private ToolbarActivity.Builder.RightMenuClickListener rightMenuClickListener;

    private TextView mCenterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    protected void initToolbar() {
        mBuilder = configToolbar();
        mToolBar = mRootView.findViewById(R.id.toolbar);
        if (mToolBar != null) {
            mToolBar.setPadding(0, (int) getResources().getDimension(R.dimen.main_statusbar_view_height), 0, 0);
            mToolBar.setBackgroundColor(mBuilder.backgroundColor != 0 ? ContextCompat.getColor(this, R.color.transparent) : ContextCompat.getColor(this, R.color.colorPrimary));
            mToolBar.setTitle(mBuilder.leftTitle);
            mCenterTitle = mRootView.findViewById(R.id.title_textview);
            mCenterTitle.setText(mBuilder.title == null ? "" : mBuilder.title);
            if (mBuilder.isShowLeftButton) {
                mToolBar.setNavigationIcon(mBuilder.leftButtonRes == 0 ? R.drawable.ic_arrow_back_white_24dp : mBuilder.leftButtonRes);
                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mBuilder.leftButtonClickListener != null) {
                            mBuilder.leftButtonClickListener.onClick(view);
                        } else {
                            finish();
                        }
                    }
                });
            }
            if (mBuilder.menu != null) {
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

    public abstract Builder configToolbar();

    public static class Builder {

        private String title = null;
        private String leftTitle = null;
        private Boolean isShowLeftButton = true;
        private Menu menu = null;
        private int leftButtonRes = 0;
        private int rightMenuRes = 0;
        private int backgroundColor = 0;
        private ToolbarActivity.Builder.LeftButtonClickListener leftButtonClickListener;
        private ToolbarActivity.Builder.RightMenuClickListener rightMenuClickListener;

        public Builder() {

        }

        public ToolbarActivity.Builder title(String var) {
            title = var;
            return this;
        }

        public ToolbarActivity.Builder Menu(Menu var) {
            menu = var;
            return this;
        }

        public ToolbarActivity.Builder leftTitle(String var) {
            leftTitle = var;
            return this;
        }

        public ToolbarActivity.Builder backgroundColor(int var) {
            backgroundColor = var;
            return this;
        }

        public ToolbarActivity.Builder isShowLeftButton(Boolean val) {
            isShowLeftButton = val;
            return this;
        }

        public ToolbarActivity.Builder leftButtonRes(int val) {
            leftButtonRes = val;
            return this;
        }

        public ToolbarActivity.Builder rightMenuRes(int val) {
            rightMenuRes = val;
            return this;
        }

        public ToolbarActivity.Builder leftButtonClickListener(ToolbarActivity.Builder.LeftButtonClickListener val) {
            leftButtonClickListener = val;
            return this;
        }

        public ToolbarActivity.Builder rightMenuClickListener(ToolbarActivity.Builder.RightMenuClickListener val) {
            rightMenuClickListener = val;
            return this;
        }

        public ToolbarActivity.Builder build() {
            return this;
        }

        public interface LeftButtonClickListener {
            void onClick(View view);
        }

        public interface RightMenuClickListener {
            void onClick(MenuItem item);
        }


    }

    public Toolbar getmToolBar() {
        return mToolBar;
    }

    public void setmToolBar(Toolbar mToolBar) {
        this.mToolBar = mToolBar;
    }

    public Builder getmBuilder() {
        return mBuilder;
    }

    public void setmBuilder(Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public String getCenterTitle() {
        return centerTitle;
    }

    public void setCenterTitle(String centerTitle) {
        this.centerTitle = centerTitle;
        mCenterTitle.setText(centerTitle);
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
        getmToolBar().setTitle(leftTitle);
    }

    public Boolean getShowLeftButton() {
        return isShowLeftButton;
    }

    public void setShowLeftButton(Boolean showLeftButton) {
        isShowLeftButton = showLeftButton;
    }

    public int getLeftButtonRes() {
        return leftButtonRes;
    }

    public void setLeftButtonRes(int leftButtonRes) {
        this.leftButtonRes = leftButtonRes;
    }

    public int getRightMenuRes() {
        return rightMenuRes;
    }

    public void setRightMenuRes(int rightMenuRes) {
        this.rightMenuRes = rightMenuRes;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Builder.LeftButtonClickListener getLeftButtonClickListener() {
        return leftButtonClickListener;
    }

    public void setLeftButtonClickListener(Builder.LeftButtonClickListener leftButtonClickListener) {
        this.leftButtonClickListener = leftButtonClickListener;
    }

    public Builder.RightMenuClickListener getRightMenuClickListener() {
        return rightMenuClickListener;
    }

    public void setRightMenuClickListener(Builder.RightMenuClickListener rightMenuClickListener) {
        this.rightMenuClickListener = rightMenuClickListener;
    }
}
