/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.api</p>
 * <p>File：OrderApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/18:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.api;

import com.tp.venus.config.Url;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Order;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.shop.api.OrderApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/18:36
 * @version 1.0.0
 */

public interface OrderApi {

    /**
     * 生成订单接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ORDER_CREATE)
    Observable<JsonMessage<Order>> createOrder(@Body Map<String, Object> params);

    /**
     * 生成订单接口(立即购买)
     * @param params
     * @return
     */
    @POST(Url.SHOP_ORDER_CREATE_BUYNOW)
    Observable<JsonMessage<Order>> buyNow(@Body Map<String, Object> params);

    /**
     * 获取订单详情接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ORDER_FIND_BY_ID)
    Observable<JsonMessage<Order>> findOrderById(@Body Map<String, Object> params);


    /**
     * 修改订单相关信息(状态)接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ORDER_UPDATE_STATUS)
    Observable<JsonMessage<Order>> updateOrderStatus(@Body Map<String, Object> params);

    /**
     * 保存售后服务信息接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_SAVE_SERVICE_INFO)
    Observable<JsonMessage<Order>> save_aftersale_service_info(@Body Map<String, Object> params);


    /**
     * 计算订单运费和应付总额接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ORDER_CALC)
    Observable<JsonMessage<Order>> calc(@Body Map<String, Object> params);

    /**
     * 购物车商品总数接口
     * @return
     */
    @POST(Url.SHOP_CART_COUNT)
    Observable<JsonMessage<Map<String, Integer>>> showBadgeView();

    /**
     * 订单支付接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ORDER_PAY)
    Observable<JsonMessage<Map<String, String>>> pay(@Body Map<String, Object> params);


    /**
     * 支付完成后APP端调用的支付完成后页面跳转接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ORDER_PAY_SUCCESS)
    Observable<JsonMessage> paySuccess(@Body Map<String, Object> params);

}
