/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IPayPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/12/14:22.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.shop.presenter.IPayPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/12/14:22
 * @version 1.0.0
 */

public interface IPayPresenter extends IBasePresenter {


    /**
     * 订单支付接口
     * @param orderId
     * @param payType
     */
    @Login
    void pay(String orderId, int payType);

    /**
     * 支付完成后APP端调用的支付完成后页面跳转接口
     * @param prepayId
     */
    @Login
    void paySuccess(String prepayId);
}
