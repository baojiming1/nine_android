/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.api</p>
 * <p>File：LiveApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/18:14.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.api;

import com.tp.venus.config.Url;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.qinjia.entity.LiveProduct;
import com.tp.venus.module.qinjia.entity.LiveProgram;

import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**<p>Class：com.tp.venus.module.qinjia.api.LiveApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/30/18:14
 * @version 1.0.0
 */

public interface LiveApi {


    /**
     * 直播节目的信息详情
     * @param programId
     * @return
     */
    @POST("/live/program/info/{programId}")
    Observable<JsonMessage<LiveProgram>> findLive(@Path("programId") String programId);

    /**
     * 直播节目的信息详情
     * @param programId
     * @return
     */
    @POST(Url.LIVE_PRODUCT)
    Observable<JsonMessage<PageResult<LiveProduct>>> findLiveProduct(@Path("programId") String programId);
}
