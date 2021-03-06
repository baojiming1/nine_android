package com.tp.venus.module.user.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tp.venus.base.dao.NAbstractDaoMaster;

import de.greenrobot.dao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class UserDaoMaster extends NAbstractDaoMaster {

    public UserDaoMaster(SQLiteDatabase db) {
        super(db);
        registerDaoClass(UserDao.class);
    }
    
    public UserDaoSession newSession() {
        return new UserDaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public UserDaoSession newSession(IdentityScopeType type) {
        return new UserDaoSession(db, type, daoConfigMap);
    }
    
}
