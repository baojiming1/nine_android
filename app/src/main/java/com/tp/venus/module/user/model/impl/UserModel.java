/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.model.impl</p>
 * <p>File：UserModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/11:39.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.model.impl;

import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.user.api.UserApi;
import com.tp.venus.module.user.bean.BindPhoneBean;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.util.ApiUtil;
import com.tp.venus.util.StringUtil;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.user.model.impl.UserModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/11:39
 * @version 1.0.0
 */

public class UserModel implements IUserModel {
    @Override
    public void userInfo(String userId, Subscriber<JsonMessage<User>> mProgressSubscriber) {
        Observable<JsonMessage<User>> ob = null;
        if(StringUtil.isNotEmpty(userId)){
            ob = ApiUtil.getInstance().createApi(UserApi.class).findInfoByUserId(userId);
        } else {
            ob = ApiUtil.getInstance().createApi(UserApi.class).findInfoByUserId();
        }
        ob.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void login(String account, String pwd, int loginType, Subscriber<JsonMessage<User>> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("pwd", pwd);
        params.put("type", loginType);
        ApiUtil.getInstance().createApi(UserApi.class).login(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void loginOut(Subscriber<JsonMessage> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(UserApi.class).loginOut().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void fastLogin(String account, String code, Subscriber<JsonMessage<User>> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", account);
        params.put("code", code);
        ApiUtil.getInstance().createApi(UserApi.class).fastLogin(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void refreshToken( Subscriber<JsonMessage<User>> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("refreshToken", 1);
        ApiUtil.getInstance().createApi(UserApi.class).refreshToken(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void register4phone(String account, String pwd, String deviceId, String nickname, String code, Subscriber<JsonMessage<User>> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("nickname", nickname);
        params.put("code", code);
        params.put("pwd", pwd);
        params.put("deviceId", deviceId);
        params.put("registerType", Status.User.MOBILE);
        ApiUtil.getInstance().createApi(UserApi.class).register(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void register4Email(String email, String nickname, String pwd, String deviceId, Subscriber<JsonMessage<User>> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("account", email);
        params.put("nickname", nickname);
        params.put("pwd", pwd);
        params.put("deviceId", deviceId);
        params.put("registerType", Status.User.EMAIL);
        ApiUtil.getInstance().createApi(UserApi.class).register(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void resetPwd(String moblie, String code, String pwd, Subscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mobile", moblie);
        params.put("code", code);
        params.put("pwd", pwd);
        ApiUtil.getInstance().createApi(UserApi.class).resetPwd(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void updatePwd(String oldPwd, String newPwd, Subscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("oldPwd", oldPwd);
        params.put("pwd", newPwd);
        ApiUtil.getInstance().createApi(UserApi.class).updatePwd(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void attention(String userId, Boolean isAttention, Subscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("id", userId);
        params.put("isAttention", isAttention);
        params.put("type", Status.Attention.USER);
        ApiUtil.getInstance().createApi(UserApi.class).attention(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void login4other(String openid, Short souce, Subscriber<JsonMessage<User>> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("openid", openid);
        params.put("source", souce);
        ApiUtil.getInstance().createApi(UserApi.class).login4other(params).
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void register4three(BindPhoneBean mBindPhoneBean, Subscriber<JsonMessage<User>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(UserApi.class).register4three(mBindPhoneBean).
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void editUserInfo(User user, Subscriber<JsonMessage> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(UserApi.class).editUserInfo(user).
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void bindPhone(String mobile, String code, Subscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", mobile);
        params.put("code", code);
        ApiUtil.getInstance().createApi(UserApi.class).bindPhone(params).
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
