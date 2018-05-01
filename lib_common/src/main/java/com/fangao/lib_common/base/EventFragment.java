package com.fangao.lib_common.base;


import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/11/24.
 */

public class EventFragment extends SupportFragment {

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
