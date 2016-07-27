/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.api</p>
 * <p>File：TagApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/20/13:06.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.api;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.content.bean.Tag;
import com.tp.venus.module.content.bean.TagResult;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**<p>Class：com.tp.venus.module.content.api.TagApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/20/13:06
 * @version 1.0.0
 */

public interface TagApi {

    /**
     * 标签详情
     * @param tagId
     * @return
     */
    @POST("/content/tag/info/{tagId}")
    Observable<JsonMessage<TagResult>> showTagInfo(@Path("tagId") String tagId);

    /**
     * 搜索热门标签
     * @return
     */
    @POST("/content/tag/hot")
    Observable<JsonMessage<PageResult<Tag>>> searchHotTag();

    /**
     * 标签关注
     * @param params
     * @return
     */
    @POST("/common/attention/save")
    Observable<JsonMessage> attention(@Body Map<String, Object> params);
}
