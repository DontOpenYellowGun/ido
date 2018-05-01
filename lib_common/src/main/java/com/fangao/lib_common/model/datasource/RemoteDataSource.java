package com.fangao.lib_common.model.datasource;

import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.func.ApiHandle;
import com.fangao.lib_common.http.server.Service;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.model.User;
import com.fangao.lib_common.util.MapSort;
import com.fangao.lib_common.util.RSAUtils2;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件描述：单据远程数据源
 * <p>
 * 作者：   Created by sven on 2017/9/26.
 */

public enum RemoteDataSource {

    INSTANCE;

    /**
     * Method:      init <br>
     * Description: 初始化 <br>
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<InitData> init() {
        return Service
                .INSTANCE
                .getApi()
                .init(MapSort.getInitMap())
                .map(new ApiHandle<InitData>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * Method:      login <br>
     * Description: 登录<br>
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<User> login(String username, String password) {
        String publicKey = ((InitData) Hawk.get(HawkConstant.INIT_DATA)).getPublicKey();
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        try {
            map.put("password", RSAUtils2.encryptByPublicKey(password, publicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Service.INSTANCE.getApi()
                .login(MapSort.getSortMap(map))
                .map(new ApiHandle<User>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * Method:      logout <br>
     * Description: 退出登录<br>
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<Object> logout(String username, String password, String verificationCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", username);
        map.put("password", password);
        map.put("verificationCode", verificationCode);
        return Service.INSTANCE.getApi()
                .logout(MapSort.getSortMap(map))
                .map(new ApiHandle<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
