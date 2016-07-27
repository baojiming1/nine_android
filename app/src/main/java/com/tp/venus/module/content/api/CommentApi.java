/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.api</p>
 * <p>File：CommentApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/21/10:57.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.api;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.bean.Comment;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.content.api.CommentApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/21/10:57
 * @version 1.0.0
 */

public interface CommentApi {

    /**
     * 保存评论
     * @param body
     * @return
     */
    @POST("/content/comment/save")
    Observable<JsonMessage<Comment>> save(@Body Map<String, String> body);
}
