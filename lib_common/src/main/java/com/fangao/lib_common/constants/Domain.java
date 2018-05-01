package com.fangao.lib_common.constants;

import android.content.Context;

import com.fangao.lib_common.util.AppUtil;

/**
 * 文件描述：域名管理
 * <p>
 * 作者：   Created by sven on 2017/11/3.
 */

public class Domain {

    private static final String BASE_URL_RELEASE = "http://app.aiti.com/api/";//release
    private static final String BASE_URL_DEBUG = "http://testapp.aiti.com/api/";//debug

    public static String getBaseUrl(Context context) {
        if (AppUtil.isApkDebugable(context)) {
            return BASE_URL_DEBUG;
        } else {
            return BASE_URL_RELEASE;
        }
    }

}
