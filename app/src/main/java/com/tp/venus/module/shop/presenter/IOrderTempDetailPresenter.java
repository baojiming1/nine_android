/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IOrderTempDetailPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/23/15:18.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.shop.presenter.IOrderTempDetailPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/23/15:18
 * @version 1.0.0
 */

public interface IOrderTempDetailPresenter extends IBasePresenter {

    /**
     * 获取当前用户的默认收货地址详情接口
     */
    @Login
    void getCurrentUserAdress();

    /**
     * 订单生成
     * @param addressId
     * @param itemIds
     * @param message
     */
    @Login
    void createOrder(String addressId, String itemIds, String message);

    /**
     * 计算订单运费和应付总额接口
     * @param itemIds 已选商品ID,多个用逗号隔开
     */
    @Login
    void calc(String itemIds);

    /**
     * 生成订单接口(立即购买)
     * @param addressId
     * @param productId
     * @param selectedSku
     * @param message
     */
    @Login
    void buyNow(String addressId, String productId, String selectedSku, String message);
}
