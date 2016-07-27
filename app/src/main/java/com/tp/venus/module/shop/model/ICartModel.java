/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model</p>
 * <p>File：ICartModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/15:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.ShopCart;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.shop.model.ICartModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/15:40
 * @version 1.0.0
 */

public interface ICartModel {


    /**
     * 加入到购物车
     * @param productId
     * @param buyCount
     * @param sku
     * @param mProgressSubscriber
     */
    void addCart(String productId, Integer buyCount, String sku, Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 购物车中的产品列表接口
     * @param mProgressSubscriber
     */
    void list(Subscriber<JsonMessage<ShopCart>> mProgressSubscriber);

    /**
     * 变更购物车商品的选购信息(个数)接口
     * @param itemId
     * @param buyCount
     */
    void changeBuyCount(String itemId, int buyCount, Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 删除购物车商品接口
     * @param mProgressSubscriber
     */
    void delete(String itemIds, Subscriber<JsonMessage> mProgressSubscriber);
}
