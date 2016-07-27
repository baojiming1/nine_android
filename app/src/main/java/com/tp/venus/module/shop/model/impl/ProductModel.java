/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.model.impl</p>
 * <p>File：ProductModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/28/15:00.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.api.ProductApi;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.model.IProductModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.shop.model.impl.ProductModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/28/15:00
 * @version 1.0.0
 */

public class ProductModel extends BaseModel implements IProductModel {

    @Override
    public void getInfo(String productId, Subscriber<JsonMessage<Product>> mProgressSubscriber) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);
        ApiUtil.getInstance().createApi(ProductApi.class).getInfo(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

}
