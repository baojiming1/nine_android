/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.api</p>
 * <p>File：ContentApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/14/11:45.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.api;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.bean.ContentResult;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**<p>Class：com.tp.venus.module.content.api.ContentApi</p>
 * <p>Description：</p>
 * <pre>
 *    帖子相关的API
 * </pre>
 * @author 鲍建明
 * @date 2016/1/14/11:45
 * @version 1.0.0
 */

public interface    ContentApi {

    /**
     * 点赞
     * @param contentId
     * @return
     */
    @POST("content/praise/{contentId}")
    Observable<JsonMessage> praise(@Path("contentId") String contentId, @Body Map<String, Boolean> praise);


    /**
     * 删除帖子
     * @param contentId
     * @return
     */
    @POST("/content/delete/{contentId}")
    Observable<JsonMessage> delete(@Path("contentId") String contentId);

    /**
     * 帖子详情
     * @param contentId
     * @return
     */
    @POST("/content/info/{contentId}")
    Observable<JsonMessage<ContentResult>> contentDetails(@Path("contentId") String contentId);

}
