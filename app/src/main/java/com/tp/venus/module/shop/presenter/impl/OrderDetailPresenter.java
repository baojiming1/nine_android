/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：OrderDetailPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/5/14:57.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.model.IOrderModel;
import com.tp.venus.module.shop.model.impl.OrderModel;
import com.tp.venus.module.shop.presenter.IOrderDetailPresenter;
import com.tp.venus.module.shop.ui.view.IOrderDetailView;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.OrderDetailPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/5/14:57
 * @version 1.0.0
 */

public class OrderDetailPresenter extends OrderBottonPresenter implements IOrderDetailPresenter {

    private IOrderDetailView iOrderDetailView;
    private IOrderModel orderModel;

    public OrderDetailPresenter(IOrderDetailView mBaseView) {
        super(mBaseView);
        this.iOrderDetailView = mBaseView;
        this.orderModel = new OrderModel();
    }

    @Override
    public void showDetail(String orderId) {
        if(StringUtil.isEmpty(orderId)){
            return ;
        }
        orderModel.findOrderById(orderId, new ProgressSubscriber<JsonMessage<Order>>(iOrderDetailView) {
            @Override
            public void onSuccess(JsonMessage<Order> message) {
                Order order = message.getObj();
                if( order == null){
                    return ;
                }
                iOrderDetailView.showDetail(order);
            }
        });
    }
}
