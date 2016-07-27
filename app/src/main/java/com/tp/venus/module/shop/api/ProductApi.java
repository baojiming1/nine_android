/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.api</p>
 * <p>File：ProductApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/19/14:53.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.api;

import com.tp.venus.config.Url;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Product;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.shop.api.ProductApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/19/14:53
 * @version 1.0.0
 */

public interface ProductApi {

    /**
     * 获取商品详情接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_PRODUCT_GET_INFO )
    Observable<JsonMessage<Product>> getInfo(@Body Map<String, String> params);
}
