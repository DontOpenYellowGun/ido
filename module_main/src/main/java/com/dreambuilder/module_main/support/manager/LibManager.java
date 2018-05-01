package com.dreambuilder.module_main.support.manager;

import android.content.Context;

import com.fangao.lib_common.manager.UserManager;
import com.fangao.lib_common.util.AppUtil;
import com.dreambuilder.module_main.model.dao.DbManager;

import io.rong.imkit.RongIM;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2018/2/10.
 */

public class LibManager {

    private static final String IM_RELEASE_APP_KEY = "qf3d5gbjqs5vh";
    private static final String IM_DEBUG_APP_KEY = "kj7swf8okiw22";

    public static void initIm(Context context) {
        String imAppKey;
        if (AppUtil.isApkDebugable(context)) {
            imAppKey=IM_DEBUG_APP_KEY;
        } else {
            imAppKey=IM_RELEASE_APP_KEY;
        }
        RongIM.init(context, imAppKey);
        ImCallBack.init(context);
        DbManager.INSTANCE.init(context);
        UserManager.INSTANCE.init();
    }
}
