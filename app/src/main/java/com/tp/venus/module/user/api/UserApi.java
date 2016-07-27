/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.api</p>
 * <p>File：UserApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/13:42.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.api;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.user.bean.BindPhoneBean;
import com.tp.venus.module.user.bean.User;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

/**<p>Class：com.tp.venus.module.user.api.UserApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/13:42
 * @version 1.0.0
 */

public interface UserApi {

    /**
     * 获取当前用户的基本数据
     */
    @POST("/user/search/info")
    Observable<JsonMessage<User>> findInfoByUserId();


    /**
     * 获取当前用户的基本数据
     */
    @POST("/user/search/info/{userId}")
    Observable<JsonMessage<User>> findInfoByUserId(@Path("userId") String userId);

    /**
     * 邮箱登录/手机号登陆
     * @param params
     * @return
     */
    @POST("/user/login")
    Observable<JsonMessage<User>> login(@Body Map<String, Object> params);

    /**
     * 登出
     * @return
     */
    @POST("/user/logout")
    Observable<JsonMessage> loginOut();

    /**
     *
     * @param params
     * @return
     */
    @POST("/user/login_by_code")
    Observable<JsonMessage<User>> fastLogin(@Body Map<String, Object> params);

    /**
     * 手机号注册/邮箱注册
     * @param params
     * @return
     */
    @POST("/user/register")
    Observable<JsonMessage<User>> register(@Body Map<String, Object> params);

    /**
     * 刷新Token
     * @param params
     * @return
     */
    @PUT("/user/token/refresh")
    Observable<JsonMessage<User>> refreshToken(@Body Map<String, Object> params);

    /**
     * 密码重置
     * @param params
     * @return
     */
    @PUT("/user/resetPwd")
    Observable<JsonMessage> resetPwd(@Body Map<String, String> params);

    /**
     * 修改密码
     * @param params
     * @return
     */
    @PUT("/user/pwd/update")
    Observable<JsonMessage> updatePwd(@Body Map<String, String> params);

    /**
     * 用户之间的关注/取消关注
     * @param params
     * @return
     */
    @POST("/common/attention/save")
    Observable<JsonMessage> attention(@Body Map<String, Object> params);

    /**
     * 第三方登录
     * @param params
     * @return
     */
    @POST("/user/login4three")
    Observable<JsonMessage<User>> login4other(@Body Map<String, Object> params);

    /**
     * 用户注册(第三方)
     * @param mBindPhoneBean
     * @return
     */
    @POST("/user/register4three")
    Observable<JsonMessage<User>> register4three(@Body BindPhoneBean mBindPhoneBean);

    /**
     * 用户编辑基本资料
     * @param user
     * @return
     */
    @POST("/user/edit")
    Observable<JsonMessage> editUserInfo(@Body User user);

    /**
     * 绑定手机号
     * @param params
     * @return
     */
    @POST("/user/bind_mobile")
    Observable<JsonMessage> bindPhone(@Body Map<String, Object> params);
}
