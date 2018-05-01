package com.fangao.lib_common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class AppUtil {
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception ignored) {
        }
        return false;
    }
}
