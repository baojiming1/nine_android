/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.model</p>
 * <p>File：IUserModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/11:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.user.bean.BindPhoneBean;
import com.tp.venus.module.user.bean.User;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.user.model.IUserModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/11:23
 * @version 1.0.0
 */

public interface IUserModel {

    /**
     * 用户基本信息
     * @param mProgressSubscriber
     */
    void userInfo(String userId, Subscriber<JsonMessage<User>> mProgressSubscriber);


    /**
     * 邮箱登陆/手机号登陆
     * @param account
     * @param pwd
     * @param loginType
     */
    void login( String account,  String pwd, int loginType, Subscriber<JsonMessage<User>> mProgressSubscriber);

    /**
     * 登出
     * @param mProgressSubscriber
     */
    void loginOut(Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 快捷登录
     * @param account
     * @param code
     * @param mProgressSubscriber
     */
    void fastLogin(String account, String code, Subscriber<JsonMessage<User>> mProgressSubscriber);

    /**
     * 刷新Token
     * @param mProgressSubscriber
     */
    void refreshToken( Subscriber<JsonMessage<User>> mProgressSubscriber);

    /**
     * 手机号注册
     * @param account
     * @param pwd
     * @param deviceId  设备唯一标识
     * @param nickname
     * @param code
     */
    void register4phone(String account, String pwd, String deviceId, String nickname, String code,  Subscriber<JsonMessage<User>> mProgressSubscriber);

    /**
     * 邮箱注册
     * @param email
     * @param nickname
     * @param pwd
     * @param mProgressSubscriber
     */
    void register4Email(String email, String nickname, String pwd, String deviceId, Subscriber<JsonMessage<User>> mProgressSubscriber);


    /**
     * 重置密码
     * @param moblie
     * @param code
     * @param pwd
     */
    void resetPwd(String moblie, String code, String pwd, Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 修改密码
     * @param oldPwd
     * @param newPwd
     */
    void updatePwd(String oldPwd, String newPwd, Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 用户关注/取消关注
     * @param userId
     * @param isAttention
     * @param mProgressSubscriber
     */
    void attention(String userId, Boolean isAttention, Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 第三方登录
     * @param openid
     * @param souce
     */
    void login4other(String openid, Short souce, Subscriber<JsonMessage<User>> mProgressSubscriber);

    /**
     * 用户第三方注册
     * @param mBindPhoneBean
     * @param mProgressSubscriber
     */
    void register4three(BindPhoneBean mBindPhoneBean, Subscriber<JsonMessage<User>> mProgressSubscriber);

    /**
     * 编辑用户基本信息
     * @param user
     * @param mProgressSubscriber
     */
    void editUserInfo(User user, Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 绑定手机号
     * @param mobile
     * @param code
     * @param mProgressSubscriber
     */
    void bindPhone(String mobile, String code, Subscriber<JsonMessage> mProgressSubscriber);
}
