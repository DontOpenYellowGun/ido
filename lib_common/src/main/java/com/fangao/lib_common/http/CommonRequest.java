package com.fangao.lib_common.http;

import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.model.datasource.RemoteDataSource;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.model.User;
import com.orhanobut.hawk.Hawk;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2018/2/10.
 */

public class CommonRequest {
    public static void getInitData(){
        RemoteDataSource.INSTANCE.init()
                .map(new Function<InitData, InitData>() {
                    @Override
                    public InitData apply(InitData initData) throws Exception {
                        Hawk.put(HawkConstant.TOKEN, initData.getToken());
                        Hawk.put(HawkConstant.INIT_DATA, initData);
                        Hawk.put(HawkConstant.PUBLIC_KEY, initData.getPublicKey());
                        Hawk.put(HawkConstant.APP_SECRET, initData.getSecret());
                        return initData;
                    }
                })
                .flatMap(new Function<InitData, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(InitData initData) throws Exception {
                        return RemoteDataSource.INSTANCE.login("15213461316","123456");
                    }
                })
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
//                        Hawk.put(HawkConstant.LOGIN_USER, user);
//                        Hawk.put(HawkConstant.TOKEN_IM, user.getUserToken());
                    }
                });
    }
}
