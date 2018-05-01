package com.fangao.health;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.bugtags.library.Bugtags;
import com.fangao.lib_common.base.BaseApplication;
import com.dreambuilder.module_main.support.manager.LibManager;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * 文件描述：
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LibManager.initIm(this);
//        initBugtags();
        initBugly();
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "5c4da901f6", false);
    }

    private void initBugtags() {
        Bugtags.start("26f8bec06e8a741deced430693661dcf", this, Bugtags.BTGInvocationEventBubble);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
