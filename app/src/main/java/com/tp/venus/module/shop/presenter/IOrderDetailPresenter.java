/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IOrderDetailPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/5/14:56.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import com.tp.venus.base.annotation.Login;

/**<p>Class：com.tp.venus.module.shop.presenter.IOrderDetailPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/5/14:56
 * @version 1.0.0
 */

public interface IOrderDetailPresenter extends IOrderBottonPresenter {

    /**
     * 显示订单详情
     * @param orderId
     */
    @Login
    void showDetail(String orderId);
}
