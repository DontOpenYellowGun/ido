package com.fangao.lib_common.model.datasource;

import com.fangao.lib_common.dao.DbManager;
import com.fangao.lib_common.model.User;
import com.fangao.lib_common.model.UserDao;

import java.util.List;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2018/2/23.
 */

public enum LocalDataSource {

    INSTANCE;

    public void addOrUpdateUser(final User... user) {
        UserDao userDao = DbManager.INSTANCE.getDaoSession().getUserDao();
        userDao.insertOrReplaceInTx(user);
    }

    public void deleteUser(final User... user) {
        UserDao userDao = DbManager.INSTANCE.getDaoSession().getUserDao();
        userDao.deleteInTx(user);
    }

    public User findUser(Long key) {
        UserDao userDao = DbManager.INSTANCE.getDaoSession().getUserDao();
        return userDao.load(key);
    }

    public List<User> findUsers() {
        UserDao userDao = DbManager.INSTANCE.getDaoSession().getUserDao();
        return userDao.loadAll();
    }

}
