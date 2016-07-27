/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.api</p>
 * <p>File：CartApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/15:42.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.api;

import com.tp.venus.config.Url;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.ShopCart;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.shop.api.CartApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/15:42
 * @version 1.0.0
 */

public interface CartApi {

    /**
     * 获取商品详情接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_CART_PRODUCT_ADD )
    Observable<JsonMessage> addProduct(@Body Map<String, Object> params);


    /**
     * 获取商品详情接口
     * @return
     */
    @POST(Url.SHOP_CART_LIST )
    Observable<JsonMessage<ShopCart>> list( );

    /**
     * 变更购物车商品的选购信息(个数)接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_CART_PRODUCT_UPDATE )
    Observable<JsonMessage> changeBuyCount(@Body Map<String, Object> params);

    /**
     * 删除购物车商品接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_CART_PRODUCT_DELETE )
    Observable<JsonMessage> delete(@Body Map<String, Object> params);
}
