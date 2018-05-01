package com.dreambuilder.module_main.model.dao;

import android.content.Context;

import com.fangao.module_main.model.DaoMaster;
import com.fangao.module_main.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by Sven on 2018-02-28.
 */

public enum DbManager {

    INSTANCE;

    private DaoSession daoSession;

    private boolean ENCRYPTED = false;

    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, ENCRYPTED ? "main-db-encrypted" : "main-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
