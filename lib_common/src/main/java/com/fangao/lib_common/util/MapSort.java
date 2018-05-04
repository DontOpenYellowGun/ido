package com.fangao.lib_common.util;


import com.fangao.lib_common.base.BaseApplication;
import com.fangao.lib_common.constants.HawkConstant;
import com.orhanobut.hawk.Hawk;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述：参数排序加密
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
public class MapSort {

    public static Map<String, Object> getSortMap(Map<String, Object> map) {
        Map<String, Object> commonParams = getCommonParams();
        map.putAll(commonParams);
        map.put("_json", "1");
        map.put("sign", getSignStr(commonParams));
        return map;
    }

    public static Map<String, Object> getLoginMap(Map<String, Object> map) {
        Map<String, Object> commonParams = getLoginParams();
        map.putAll(commonParams);
        map.put("_json", "1");
        map.put("sign", getSignStr(commonParams));
        return map;
    }

    public static Map<String, Object> getInitMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("device", "android");
        map.put("uuid", DeviceUtils.getUniquePsuedoID());
        map.put("version", String.valueOf(DeviceUtils.getVersionName(BaseApplication.getInstance())));
        map.put("brand", DeviceUtils.getPhoneBrand());
        map.put("osVersion", DeviceUtils.getSystemVersion());
        map.put("phoneModel", DeviceUtils.getPhoneModel());
        map.put("appKey", "admin");
        map.put("timestamp", System.currentTimeMillis());
        map.put("sourceQuotient", "");
        map.put("_json", "1");
        return map;
    }

    //获取签名串
    private static String getSignStr(Map<String, Object> commonParams) {
        // 对参数名进行字典排序
        String[] keyArray = commonParams.keySet().toArray(new String[commonParams.size()]);
        Arrays.sort(keyArray);
        // 拼接有序的参数名-值串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Hawk.get(HawkConstant.APP_SECRET));
        for (String key : keyArray) {
            stringBuilder.append(key).append("&").append(commonParams.get(key));
        }
        stringBuilder.append(Hawk.get(HawkConstant.APP_SECRET));
        String code = stringBuilder.toString();
        String s1 = new String(Hex.encodeHex(DigestUtils.sha1(code))).toUpperCase();
        String s2 = EncryptUtils.shaEncrypt(code).toUpperCase();
        return s2;
    }

    public static Map<String, Object> getCommonParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "admin");
        map.put("timestamp", System.currentTimeMillis());
        map.put("device", "android");
        map.put("uuid", DeviceUtils.getUniquePsuedoID());
        map.put("version", String.valueOf(DeviceUtils.getVersionName(BaseApplication.getInstance())));
        map.put("token", Hawk.get(HawkConstant.TOKEN));
        return map;
    }


    public static Map<String, Object> getLoginParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("appKey", "admin");
        map.put("timestamp", System.currentTimeMillis());
        map.put("device", "android");
        map.put("uuid", DeviceUtils.getUniquePsuedoID());
        map.put("version", String.valueOf(DeviceUtils.getVersionName(BaseApplication.getInstance())));
        map.put("token", Hawk.get(HawkConstant.TOKEN));
        return map;
    }
}
