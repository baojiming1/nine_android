/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.api</p>
 * <p>File：AddressApi.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/23/15:14.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.api;

import com.tp.venus.config.Url;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Address;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**<p>Class：com.tp.venus.module.shop.api.AddressApi</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/23/15:14
 * @version 1.0.0
 */

public interface AddressApi {

    /**
     * 获取当前用户的默认收货地址详情接口
     * @return
     */
    @POST(Url.SHOP_ADDRESS_DEFAULT_INFO)
    Observable<JsonMessage<Address>>  getCurrentUseerAddress();

    /**
     * 保存/修改收货地址接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ADDRESS_SAVE_UPDATE)
    Observable<JsonMessage>  saveOrUpdate(@Body  Map<String, Object> params);

    /**
     *设置默认收货地址接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ADDRESS_SET_DEFAULT)
    Observable<JsonMessage> setDefault(@Body  Map<String, Object> params);

    /**
     * 删除收货地址接口
     * @param params
     * @return
     */
    @POST(Url.SHOP_ADDRESS_DELETE)
    Observable<JsonMessage> delete(@Body  Map<String, Object> params);
}
