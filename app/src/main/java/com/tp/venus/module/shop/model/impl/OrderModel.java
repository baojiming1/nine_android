/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model.impl</p>
 * <p>File：OrderModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/18:34.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.api.OrderApi;
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.model.IOrderModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.shop.model.impl.OrderModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/18:34
 * @version 1.0.0
 */

public class OrderModel extends BaseModel implements IOrderModel {
    @Override
    public void createOrder(String addressId, String itemIds, String message, Subscriber<JsonMessage<Order>> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("addressId", addressId);
        params.put("itemIds", itemIds);
        params.put("message", message);
        ApiUtil.getInstance().createApi(OrderApi.class).createOrder(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void buyNow(String addressId, String productId, String selectedSku, String message, Subscriber<JsonMessage<Order>> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("addressId", addressId);
        params.put("productId", productId);
        params.put("selectedSku", selectedSku);
        params.put("message", message);
        ApiUtil.getInstance().createApi(OrderApi.class).buyNow(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void findOrderById(String orderId, Subscriber<JsonMessage<Order>> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        ApiUtil.getInstance().createApi(OrderApi.class).findOrderById(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void updateOrderStatus(String orderId, int orderStatus, Subscriber<JsonMessage<Order>> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("orderStatus", orderStatus);
        ApiUtil.getInstance().createApi(OrderApi.class).updateOrderStatus(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void save_aftersale_service_info(String orderId, String phone, String remark, Subscriber<JsonMessage<Order>> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("remark", remark);
        params.put("phoneNumber", phone);
        ApiUtil.getInstance().createApi(OrderApi.class).save_aftersale_service_info(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void calc(String itemIds, Subscriber<JsonMessage<Order>> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("itemIds", itemIds);
        ApiUtil.getInstance().createApi(OrderApi.class).calc(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void showBadgeView(Subscriber<JsonMessage<Map<String, Integer>>> mSubscriber) {
        ApiUtil.getInstance().createApi(OrderApi.class).showBadgeView().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void pay(String orderId, int payType, Subscriber<JsonMessage<Map<String, String>>> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("payType", payType);
        ApiUtil.getInstance().createApi(OrderApi.class).pay(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void paySuccess(String prepayId, Subscriber<JsonMessage> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("prepayId", prepayId);
        ApiUtil.getInstance().createApi(OrderApi.class).paySuccess(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }
}
