/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model.impl</p>
 * <p>File：CartModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/15:41.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.api.CartApi;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.model.ICartModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.shop.model.impl.CartModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/15:41
 * @version 1.0.0
 */

public class CartModel extends BaseModel implements ICartModel {
    @Override
    public void addCart(String productId, Integer buyCount, String sku, Subscriber<JsonMessage> mProgressSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        params.put("buyCount", buyCount);
        params.put("selectedSku", sku);
        ApiUtil.getInstance().createApi(CartApi.class).addProduct(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void list(Subscriber<JsonMessage<ShopCart>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(CartApi.class).list().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void changeBuyCount(String itemId, int buyCount, Subscriber<JsonMessage> mProgressSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("itemId", itemId);
        params.put("buyCount", buyCount);
        ApiUtil.getInstance().createApi(CartApi.class).changeBuyCount(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void delete(String itemIds, Subscriber<JsonMessage> mProgressSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("itemIds", itemIds);            //商品ID ,支持多个,用过逗号隔开
        ApiUtil.getInstance().createApi(CartApi.class).delete(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
