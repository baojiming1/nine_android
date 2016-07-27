/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.api</p>
 * <p>File：MessageApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/14:55.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.api;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.message.bean.BadgeCount;

import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**<p>Class：com.tp.venus.module.message.api.MessageApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/14:55
 * @version 1.0.0
 */

public interface MessageApi {

    /**
     * 获取当前用户未读的信息
     * @return
     */
    @POST("common/message/search/count")
    Observable<JsonMessage<BadgeCount>> getBadgeCount();


    /**
     * 信息删除
     * @param messageId
     * @return
     */
    @POST("/common/message/delete/{messageId}")
    Observable<JsonMessage> delete(@Path("messageId") String messageId);
}
