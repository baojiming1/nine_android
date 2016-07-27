/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.common.api</p>
 * <p>File：FavoriteApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/11/10:17.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.api;

import com.tp.venus.model.JsonMessage;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.common.api.FavoriteApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/11/10:17
 * @version 1.0.0
 */

public interface FavoriteApi {


    /**
     * 收藏/取消收藏
     * @param praise
     * @return
     */
    @POST("/common/favorite/save")
    Observable<JsonMessage> favorite(@Body Map<String, Object> praise);
}
