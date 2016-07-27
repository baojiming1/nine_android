/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.api</p>
 * <p>File：BannerApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/16:52.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.api;

import com.tp.venus.config.Url;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.bean.Banner;

import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**<p>Class：com.tp.venus.module.common.api.BannerApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/16:52
 * @version 1.0.0
 */

public interface BannerApi {

    /**
     * 搜索banner
     * @param type
     * @return
     */
    @POST(Url.BANNER_SEARCH + "{type}")
    Observable<JsonMessage<PageResult<Banner>>> search(@Path("type") int type);

}
