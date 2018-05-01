package com.fangao.lib_common.manager;

import com.fangao.lib_common.constants.HawkConstant;
import com.fangao.lib_common.model.User;
import com.fangao.lib_common.model.datasource.LocalDataSource;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 文件描述：用户管理类
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public enum UserManager {

    INSTANCE;

    public void init() {

    }

    public User getCurrentUser() {
        Long id = Hawk.get(HawkConstant.LOGIN_USER_ID);
        return LocalDataSource.INSTANCE.findUser(id);
    }

    public void setCurrentUser(User user) {
        if (user != null) {
            Hawk.put(HawkConstant.LOGIN_USER_ID, user.getId());
            addOrUpdateUser(user);
        } else {
            Hawk.delete(HawkConstant.LOGIN_USER_ID);
        }
    }

    public void addLoginUser(User user) {
        HashMap<String, User> loginUsers = Hawk.get(HawkConstant.LOGIN_USERS);
        if (loginUsers == null) {
            loginUsers = new HashMap<>();
        }
        loginUsers.put(user.getId().toString(), user);
        Hawk.put(HawkConstant.LOGIN_USERS, loginUsers);
    }

    public List<User> getLoginUsers() {
        HashMap<String, User> loginUsers = Hawk.get(HawkConstant.LOGIN_USERS);
        List<User> loginUsersList = new ArrayList<>();
        if (loginUsers != null) {
            for (String s : loginUsers.keySet()) {
                loginUsersList.add(loginUsers.get(s));
            }
        }
        return loginUsersList;
    }

    public User getCurrentLoginUser() {
        HashMap<String, User> loginUsers = Hawk.get(HawkConstant.LOGIN_USERS);
        Long id = Hawk.get(HawkConstant.LOGIN_USER_ID);
        User user = null;
        if (loginUsers != null && id != null) {
            user = loginUsers.get(String.valueOf(id));
        }
        return user;
    }

    public void setLoginUesrAutoLoginStatus() {
        HashMap<String, User> loginUsers = Hawk.get(HawkConstant.LOGIN_USERS);
        Long id = Hawk.get(HawkConstant.LOGIN_USER_ID);
        User user;
        if (loginUsers != null && id != null) {
            user = loginUsers.get(String.valueOf(id));
            if (user != null) {
                user.setAutoLogin(false);
                loginUsers.put(String.valueOf(user.getId()), user);
                Hawk.put(HawkConstant.LOGIN_USERS,loginUsers);
            }
        }
    }

    public void deleteLoginUser(User user) {
        HashMap<String, User> loginUsers = Hawk.get(HawkConstant.LOGIN_USERS);
        if (loginUsers != null) {
            loginUsers.remove(user.getId().toString());
        }
        Hawk.put(HawkConstant.LOGIN_USERS, loginUsers);
    }

    public void updateCurrentUser(User user) {
        LocalDataSource.INSTANCE.addOrUpdateUser(user);
    }

    public void addOrUpdateUser(User... user) {
        LocalDataSource.INSTANCE.addOrUpdateUser(user);
    }

    public List<User> getAllUser() {
        return LocalDataSource.INSTANCE.findUsers();
    }

    public void loginOut() {
        setLoginUesrAutoLoginStatus();
        User currentUser = UserManager.INSTANCE.getCurrentUser();
        UserManager.INSTANCE.updateCurrentUser(currentUser);
    }
}
