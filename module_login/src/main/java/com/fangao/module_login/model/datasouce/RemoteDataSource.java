package com.fangao.module_login.model.datasouce;

import com.avos.avoscloud.AVObject;
import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.http.client.subscribers.func.BaseEntity;
import com.fangao.lib_common.http.client.subscribers.func.ApiHandle;
import com.fangao.lib_common.model.InitData;
import com.fangao.lib_common.model.User;
import com.fangao.lib_common.model.datasource.LocalDataSource;
import com.fangao.lib_common.util.MapSort;
import com.fangao.lib_common.util.RSAUtils2;
import com.fangao.lib_common.util.Validator;
import com.fangao.module_login.support.datasource.remote.Service;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
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
    public Observable<BaseEntity<InitData>> init() {
        return Service
                .INSTANCE
                .getApi()
                .init(MapSort.getInitMap())
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
        String encryptPwd = RSAUtils2.encryptByPublicKey(password, String.valueOf(Hawk.get(HawkConstant.PUBLIC_KEY)));
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", encryptPwd);
        HashMap<String, User> welcomeUsers = Hawk.get(HawkConstant.WELCOME_USERS);
        boolean isFirst = true;
        ArrayList<String> strings = new ArrayList<>();
        if (welcomeUsers != null) {
            for (String s : welcomeUsers.keySet()) {
                User user = welcomeUsers.get(s);
                strings.add(user.getLoginName());
            }
            if (strings.contains(username)) {
                isFirst = false;
            } else {
                isFirst = true;
            }
        }
        map.put("first", isFirst);
        return Service
                .INSTANCE
                .getApi()
                .login(MapSort.getSortMap(map))
                .map(new ApiHandle<User>())
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        LocalDataSource.INSTANCE.addOrUpdateUser(user);
                        return user;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * Method:      register <br>
     * Description: 注册<br>
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<BaseEntity> register(String username, String password, String verificationCode) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("phone", username);
        map.put("password", RSAUtils2.encryptByPublicKey(password, String.valueOf(Hawk.get(HawkConstant.PUBLIC_KEY))));
        map.put("verificationCode", verificationCode);
        return Service.INSTANCE.getApi()
                .register(MapSort.getSortMap(map))
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

    /**
     * Method:      getVerifyCode <br>
     * Description: 发送验证码<br>
     * Params
     * 1	val	string	是	手机号或邮箱
     * 2	categoryCode	string	是	验证码类别:
     * 1：注册
     * 2：找回密码
     * 3：修改密码
     * 4、修改手机号或邮箱
     * 3	type	string	是	短信还是邮件：
     * 短信：phone
     * 邮件：email
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<BaseEntity> getVerifyCode(String username, String categoryCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("val", username);
        map.put("categoryCode", categoryCode);
        if (Validator.isMobile(username)) {
            map.put("type", "phone");
        } else if (Validator.isEmail(username)) {
            map.put("type", "email");
        }
        return Service.INSTANCE.getApi()
                .getVerifyCode(MapSort.getSortMap(map))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * Method:      checkCode <br>
     * Description: 校验验证码<br>
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<Integer> checkCode(String username, String verifyCode, String categoryCode) {

        Map<String, Object> map = new HashMap<>();
        map.put("val", username);
        map.put("verificationCode", verifyCode);
        map.put("categoryCode", categoryCode);
        if (Validator.isMobile(username)) {
            map.put("type", "phone");
        } else if (Validator.isEmail(username)) {
            map.put("type", "email");
        }

        return Service.INSTANCE.getApi()
                .checkCode(MapSort.getSortMap(map))
                .map(new Function<BaseEntity, Integer>() {
                    @Override
                    public Integer apply(BaseEntity abs) throws Exception {
                        return abs.getStatusCode();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * Method:      checkUserName <br>
     * Description: 校验用户名<br>
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<BaseEntity> checkUserName(String phone) {

        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        return Service.INSTANCE.getApi()
                .checkUserName(MapSort.getSortMap(map))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * Method:      changePassword <br>
     * Description: 修改密码<br>
     * Params
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<Object> changePassword(String type, String val, String password, String verificationCode) {
        String publicKey = ((InitData) Hawk.get(HawkConstant.INIT_DATA)).getPublicKey();
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("val", val);
        map.put("password", RSAUtils2.encryptByPublicKey(password, publicKey));
        map.put("verificationCode", verificationCode);
        return com.fangao.lib_common.http.server.Service.INSTANCE.getApi()
                .changePassword(MapSort.getSortMap(map))
                .map(new ApiHandle<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * Method:      findPassword <br>
     * Description: 找回密码<br>
     * Params
     * Creator:     sven <br>
     * Date:        2017/12/4 下午2:07
     */
    public Observable<BaseEntity> findPassword(String type, String val, String password, String verificationCode) {
        String publicKey = ((InitData) Hawk.get(HawkConstant.INIT_DATA)).getPublicKey();
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("val", val);
        map.put("password", RSAUtils2.encryptByPublicKey(password, publicKey));
        map.put("verificationCode", verificationCode);
        return com.fangao.lib_common.http.server.Service.INSTANCE.getApi()
                .findPassword(MapSort.getSortMap(map))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

}
