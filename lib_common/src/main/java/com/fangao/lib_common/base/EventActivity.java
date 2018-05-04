package com.fangao.lib_common.base;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2018/2/15.
 */

public class EventActivity extends SupportActivity {

    protected Boolean useEventBus = false;

    @Override
    public void onStart() {
        super.onStart();
        if (getUseEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getUseEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    public Boolean getUseEventBus() {
        return useEventBus;
    }

    public void setUseEventBus(Boolean useEventBus) {
        this.useEventBus = useEventBus;
    }
}
