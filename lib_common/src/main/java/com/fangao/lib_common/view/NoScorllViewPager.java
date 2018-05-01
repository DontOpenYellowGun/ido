package com.fangao.lib_common.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2018/3/17.
 */

public class NoScorllViewPager extends ViewPager {
    public boolean isCanScroll = true;

    public NoScorllViewPager(Context context) {
        this(context, null);
    }

    public NoScorllViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    //第一种
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isCanScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!isCanScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

//    //第二种
//    @Override
//    public void scrollTo(int x, int y) {
//        if (isCanScroll) {
//            super.scrollTo(x, y);
//        }
//    }
}

