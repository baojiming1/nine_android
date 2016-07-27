/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.dao</p>
 * <p>File：UserDao.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/25/13:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.tp.venus.module.user.bean.User;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**<p>Class：com.tp.venus.module.user.dao.UserDao</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/25/13:40
 * @version 1.0.0
 */

public class UserDao extends AbstractDao<User, String> {

    public static final String TABLENAME = "USER";

    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");              //主键
        public final static Property NUMBER = new Property(1, String.class, "number", false, "NUMBER");   //qbt号
        public final static Property MOBILE = new Property(2, String.class, "mobile", false, "MOBILE");     //手机号
        public final static Property EMAIL = new Property(3, String.class, "email", false, "EMAIL");        //邮箱
        public final static Property AGE = new Property(4, Integer.class, "age", false, "AGE");             //年龄
        public final static Property NICKNAME = new Property(5, String.class, "nickname", false, "NICKNAME");       //昵称
        public final static Property SIGNATURE = new Property(6, String.class, "signature", false, "SIGNATURE");    //个性签名
        public final static Property GENDER = new Property(7, Integer.class, "gender", false, "GENDER");            //性别
        public final static Property ICON = new Property(8, String.class, "icon", false, "ICON");                   //头像
        public final static Property BIRTHDAY = new Property(9, String.class, "birthday", false, "BIRTHDAY");      //生日
        public final static Property TYPE = new Property(10, Integer.class, "type", false, "TYPE");                 //类型
        public final static Property CREATETIME = new Property(11, String.class, "createTime", false, "CREATETIME");       //创建时间
        public final static Property UPDATETIME = new Property(12, String.class, "updateTime", false, "UPDATETIME");       //修改时间
        public final static Property STATUS = new Property(13, Integer.class, "status", false, "STATUS");                   //状态
        public final static Property TOKEN = new Property(14, String.class, "token", false, "TOKEN");                       //token
        public final static Property ADDRESS = new Property(15, String.class, "ADDRESS", false, "address");                 //地址
        public final static Property USEINTEGRAL = new Property(16, Integer.class, "useIntegral", false, "USEINTEGRAL");        //可用积分
        public final static Property COUNTINTEGRAL = new Property(17, Long.class, "countIntegral", false, "COUNTINTEGRAL");      //历史总积分
        public final static Property AUTHENTICATION = new Property(18, String.class, "authentication", false, "AUTHENTICATION");           //认证状态
        public final static Property RECOMMEND = new Property(19, String.class, "recommend", false, "RECOMMEND");              //是否是推荐用户
        public final static Property EXPIRED = new Property(20, String.class, "expired", false, "EXPIRED");                    //token过期时间
    }


    public UserDao(DaoConfig config) {
        super(config);
    }

    public UserDao(DaoConfig config, UserDaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'USER' (" + //
                "'ID' INTEGER PRIMARY KEY ," + // 0: id
                "'NUMBER' varchar(30)," + // 1: number
                "'MOBILE' varchar(30)," +   //2.mobile
                "'EMAIL' varchar(60)," +  //3.email
                "'AGE' INTEGER," +   //4.age
                "'NICKNAME' varchar(60)," + //5.nickname
                "'SIGNATURE' varchar(200)," + //6.signature
                "'GENDER' INTEGER," + //7.gender
                "'ICON' varchar(120)," + //8.icon
                "'BIRTHDAY' varchar(20)," + //9.birthday              TODO 修改类型
                "'TYPE' INTEGER," + //10.type
                "'CREATETIME' varchar(20)," + //11.createTime
                "'UPDATETIME' varchar(20)," + //12.updateTime
                "'STATUS' INTEGER," + //13.status
                "'TOKEN' varchar(40)," + //14.token
                "'ADDRESS' varchar(120)," + //15.address
                "'USEINTEGRAL' INTEGER," + //16.useIntegral
                "'COUNTINTEGRAL' INTEGER(20)," + //17.countIntegral
                "'AUTHENTICATION' char(4)," + //18.authentication
                "'RECOMMEND' char(4)," + //19.recommend
                "'expired' varchar(20));" ); //20.expired
                //"'HIRE_DATE' INTEGER);"); // 2: hireDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'USER'";
        db.execSQL(sql);
    }


    @Override
    protected User readEntity(Cursor cursor, int offset) {
        User user = new User();
        user.setId(cursor.getString(offset + 0));
        user.setNumber(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        user.setMobile(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        user.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        user.setAge(cursor.getInt(offset + 4));
        user.setNickname(cursor.getString(offset + 5));
        user.setSignature(cursor.getString(offset + 6));
        user.setGender(cursor.getShort(offset + 7));
        user.setIcon(cursor.getString(offset + 8));
        user.setBirthday(Long.valueOf( cursor.getString(offset + 9)) );
        user.setType(cursor.getShort(offset + 10));
        user.setCreateTime( cursor.getLong(offset + 11) );
        user.setUpdateTime( cursor.getLong(offset + 12)  );
        user.setStatus(cursor.getShort(offset + 13));
        user.setToken(cursor.getString(offset + 14));
        user.setAddress(cursor.getString(offset + 15));
        user.setUseIntegral(cursor.getInt(offset + 16));
        user.setCountIntegral(cursor.getLong(offset + 17));
        return user;
    }

    @Override
    protected String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, User user, int offset) {
        user.setId(cursor.getString(offset + 0));
        user.setNumber(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        user.setMobile(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        user.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        user.setAge(cursor.getInt(offset + 4));
        user.setNickname(cursor.getString(offset + 5));
        user.setSignature(cursor.getString(offset + 6));
        user.setGender(cursor.getShort(offset + 7));
        user.setIcon(cursor.getString(offset + 8));
        user.setBirthday(Long.valueOf( cursor.getString(offset + 9)) );
        user.setType(cursor.getShort(offset + 10));
        user.setCreateTime(Long.valueOf(  cursor.getString(offset + 11) ));
        user.setUpdateTime(Long.valueOf( cursor.getString(offset + 12) ));
        user.setStatus(cursor.getShort(offset + 13));
        user.setToken(cursor.getString(offset + 14));
        user.setAddress(cursor.getString(offset + 15));
        user.setUseIntegral(cursor.getInt(offset + 16));
        user.setCountIntegral(cursor.getLong(offset + 17));
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getId());
        stmt.bindString(3, entity.getMobile() == null ? "" : entity.getMobile());
        stmt.bindString(4, entity.getEmail() == null ? "" : entity.getEmail());
        stmt.bindDouble(5, entity.getAge());
        stmt.bindString(6, entity.getNickname() == null ? "" : entity.getNickname());
        stmt.bindString(7, entity.getSignature() == null ? "" : entity.getSignature());
        stmt.bindDouble(8, entity.getGender());
        stmt.bindString(9, entity.getIcon() == null ? "" : entity.getIcon());
        stmt.bindString(10, entity.getType().toString());
        stmt.bindString(15, entity.getToken());

    }

    @Override
    protected String updateKeyAfterInsert(User entity, long rowId) {
        return entity.getId();
    }

    @Override
    protected String getKey(User entity) {
        if( entity == null){
            return null;
        }
        return entity.getId();
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }


}
