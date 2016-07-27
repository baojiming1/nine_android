/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.model</p>
 * <p>File：IProductModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/28/14:59.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Product;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.shop.model.IProductModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/28/14:59
 * @version 1.0.0
 */

public interface IProductModel {

    /**
     * 获取商品详情接口
     * @param productId
     * @param mProgressSubscriber
     */
    void getInfo(String productId, Subscriber<JsonMessage<Product>> mProgressSubscriber);
}
