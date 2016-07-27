/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.dao</p>
 * <p>File：NAbstractDaoSession.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/9/15:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.dao;

import android.database.sqlite.SQLiteDatabase;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;

/**<p>Class：com.tp.venus.base.dao.NAbstractDaoSession</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/9/15:36
 * @version 1.0.0
 */

public abstract class NAbstractDaoSession extends AbstractDaoSession {
    public NAbstractDaoSession(SQLiteDatabase db) {
        super(db);
    }

    protected abstract void clear();

    protected abstract AbstractDao<?, ?> getDao();
}
