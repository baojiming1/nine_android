/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.api</p>
 * <p>File：ClassesApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/16:24.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.api;

import com.tp.venus.config.Url;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.shop.bean.Classes;

import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.shop.api.ClassesApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/16:24
 * @version 1.0.0
 */

public interface ClassesApi {


    /**
     * 首页商品分类列表接口
     * @return
     */
    @POST(Url.SHOP_CLASSES_HOME)
    Observable<JsonMessage<PageResult<Classes>>> getHomeClasses();

}
