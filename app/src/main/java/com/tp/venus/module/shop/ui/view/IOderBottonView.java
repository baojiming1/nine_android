/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IOderBottonView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/6/14:26.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.shop.bean.Order;

/**<p>Class：com.tp.venus.module.shop.ui.view.IOderBottonView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/6/14:26
 * @version 1.0.0
 */

public interface IOderBottonView extends BaseView {

    /**
     * 修改订单相关信息(状态)接口
     */
    void updateOrderStatusSuccess(Order order, int position);

    /**
     * 保存售后服务信息接口成功
     */
    void saveServiceInfoSuccess(Order mOrder, int position);

}
