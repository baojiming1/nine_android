/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model</p>
 * <p>File：IOrderModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/18:34.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Order;

import java.util.Map;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.shop.model.IOrderModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/18:34
 * @version 1.0.0
 */

public interface IOrderModel {

    /**
     * 生成订单接口
     * @param addressId
     * @param productIds
     * @param message
     * @param mSubscriber
     */
    void createOrder(String addressId, String itemIds, String message, Subscriber<JsonMessage<Order>> mSubscriber);

    /**
     * 生成订单接口(立即购买)
     * @param addressId
     * @param productId
     * @param selectedSku
     * @param message
     * @param mSubscriber
     */
    void buyNow(String addressId, String productId, String selectedSku, String message, Subscriber<JsonMessage<Order>> mSubscriber);

    /**
     * 获取订单详情接口
     * @param orderId
     * @param mSubscriber
     */
    void findOrderById(String orderId, Subscriber<JsonMessage<Order>> mSubscriber);

    /**
     * 修改订单相关信息(状态)接口
     * @param orderId
     * @param orderStatus
     * @param mSubscriber
     */
    void updateOrderStatus(String orderId, int orderStatus, Subscriber<JsonMessage<Order>> mSubscriber);

    /**
     * 保存售后服务信息接口
     * @param orderId
     * @param phone
     * @param remark
     * @param mSubscriber
     */
    void save_aftersale_service_info(String orderId, String phone, String remark, Subscriber<JsonMessage<Order>> mSubscriber);

    /**
     * 已选商品ID,多个用逗号隔开
     * @param productIds
     */
    void calc(String itemIds, Subscriber<JsonMessage<Order>> mSubscriber);

    /**
     * 购物车商品总数接口
     * @param mSubscriber
     */
    void showBadgeView(Subscriber<JsonMessage<Map<String, Integer>>> mSubscriber);

    /**
     * 支付
     * @param orderId
     * @param payType
     * @param mSubscriber
     */
    void pay(String orderId, int payType, Subscriber<JsonMessage<Map<String, String>>> mSubscriber );

    /**
     * 支付完成后APP端调用的支付完成后页面跳转接口
     * @param prepayId
     * @param mSubscriber
     */
    void paySuccess(String prepayId, Subscriber<JsonMessage> mSubscriber);
}
