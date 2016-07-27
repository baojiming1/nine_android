/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.dao</p>
 * <p>File：UserDataManager.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/25/15:25.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.tp.venus.base.dao.DatabaseManager;
import com.tp.venus.base.dao.NAbstractDaoMaster;
import com.tp.venus.base.dao.NAbstractDaoSession;
import com.tp.venus.module.user.bean.User;

import de.greenrobot.dao.AbstractDao;

/**<p>Class：com.tp.venus.module.user.dao.UserDataManager</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/25/15:25
 * @version 1.0.0
 */

public class UserDataManager extends DatabaseManager<User, String> {
    public UserDataManager(@NonNull Context context) {
        super(context);
    }

    @Override
    protected NAbstractDaoMaster getDaoMaster(SQLiteDatabase database) {
        return new UserDaoMaster(database);
    }

    @Override
    protected NAbstractDaoSession getDaoSession(NAbstractDaoMaster daoMaster) {
        return ((UserDaoMaster)daoMaster).newSession();
    }

    @Override
    public AbstractDao<User, String> getAbstractDao(NAbstractDaoSession mNAbstractDaoSession) {
        return ((UserDaoSession)mNAbstractDaoSession).getDao();
    }
}
