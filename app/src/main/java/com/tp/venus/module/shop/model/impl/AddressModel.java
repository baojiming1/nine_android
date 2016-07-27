/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model.impl</p>
 * <p>File：AddressModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/23/15:07.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.api.AddressApi;
import com.tp.venus.module.shop.bean.Address;
import com.tp.venus.module.shop.model.IAddressModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.shop.model.impl.AddressModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/23/15:07
 * @version 1.0.0
 */

public class AddressModel extends BaseModel implements IAddressModel {
    @Override
    public void getCurrentUseerAddress(Subscriber<JsonMessage<Address>> mSubscriber) {
        ApiUtil.getInstance().createApi(AddressApi.class).getCurrentUseerAddress().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void save(String name, String mobile, String address,  boolean defaultAddress, Subscriber<JsonMessage> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("recipientName", name);
        params.put("detail", address);
        params.put("mobile", mobile);
        params.put("isDefault", defaultAddress);
        ApiUtil.getInstance().createApi(AddressApi.class).saveOrUpdate(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void update(String id, String name, String mobile, String address,  boolean defaultAddress, Subscriber<JsonMessage> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("addressId", id);
        params.put("recipientName", name);
        params.put("detail", address);
        params.put("mobile", mobile);
        params.put("isDefault", defaultAddress);
        ApiUtil.getInstance().createApi(AddressApi.class).saveOrUpdate(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void setDefault(String addressId, Subscriber<JsonMessage> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("addressId", addressId);
        ApiUtil.getInstance().createApi(AddressApi.class).setDefault(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }

    @Override
    public void delete(String addressId, Subscriber<JsonMessage> mSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("addressId", addressId);
        ApiUtil.getInstance().createApi(AddressApi.class).delete(params).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }
}
