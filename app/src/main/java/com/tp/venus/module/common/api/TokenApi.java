/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.api</p>
 * <p>File：TokenApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/17/17:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.api;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.bean.Token;

import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.common.api.TokenApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/17/17:23
 * @version 1.0.0
 */

public interface TokenApi {

    /**
     * 获取token
     * @return
     */
    @POST("/common/token")
    Observable<JsonMessage<Token>> getToken();
}
