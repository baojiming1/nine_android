/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IOrderDetailView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/5/14:52.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import com.tp.venus.module.shop.bean.Order;

/**<p>Class：com.tp.venus.module.shop.ui.view.IOrderDetailView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/5/14:52
 * @version 1.0.0
 */

public interface IOrderDetailView extends IOderBottonView {

    /**
     * 显示订单详情信息
     * @param order
     */
    void showDetail(Order order);

}
