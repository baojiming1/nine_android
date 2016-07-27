/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model</p>
 * <p>File：IAddressModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/23/15:07.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Address;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.shop.model.IAddressModel</p>
 * <p>Description：</p>
 * <pre>
 *      收货地址
 * </pre>
 * @author 鲍建明
 * @date 2016/4/23/15:07
 * @version 1.0.0
 */

public interface IAddressModel {

    /**
     *  获取当前用户的默认收货地址详情接口
     * @param mSubscriber
     */
    void getCurrentUseerAddress(Subscriber<JsonMessage<Address>> mSubscriber);

    /**
     *保存收货地址接口
     * @param name
     * @param mobile
     * @param address
     * @param idcard
     */
    void save(String name, String mobile, String address,  boolean defaultAddress, Subscriber<JsonMessage> mSubscriber);

    /**
     *修改收货地址接口
     * @param id
     * @param name
     * @param mobile
     * @param address
     * @param idcard
     */
    void update(String id, String name, String mobile, String address,  boolean defaultAddress, Subscriber<JsonMessage> mSubscriber);

    /**
     * 设置默认收货地址接口
     * @param addressId
     * @param mSubscriber
     */
    void setDefault(String addressId, Subscriber<JsonMessage> mSubscriber);

    /**
     * 删除收货地址接口
     * @param addressId
     * @param mSubscriber
     */
    void delete(String addressId, Subscriber<JsonMessage> mSubscriber);
}
