package debug;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.fangao.lib_common.base.BaseApplication;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/9/25.
 */

public class DebugApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
//        LibManager.initIm(this);
//        CommonRequest.getInitData();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
