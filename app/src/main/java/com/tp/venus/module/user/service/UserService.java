/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.service</p>
 * <p>File：UserService.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/7/14:28.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.service;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.user.bean.User;

import retrofit.Callback;
import retrofit.http.GET;

/**<p>Class：com.tp.venus.module.user.service.UserService</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/7/14:28
 * @version 1.0.0
 */

public interface UserService {

    /**
     * 获取推荐用户
     * @param page
     * @return
     */
    @GET("/user/recommend")
    void getRecommend( Callback<JsonMessage<PageResult<User>>> callback);

}
