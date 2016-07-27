/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IOrderTempDetailView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/23/15:21.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.shop.bean.Address;
import com.tp.venus.module.shop.bean.Order;

/**<p>Class：com.tp.venus.module.shop.ui.view.IOrderTempDetailView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/23/15:21
 * @version 1.0.0
 */
public interface IOrderTempDetailView extends BaseView {

    /**
     * 显示地址信息
     * @param mAddress
     */
    void showAddress(Address mAddress);

    /**
     * 生成订单成功
     */
    void createOrderSuccess(Order mOrder);

    /**
     * 计算订单运费和应付总额接口
     * @param order
     */
    void calc(Order order);
}
