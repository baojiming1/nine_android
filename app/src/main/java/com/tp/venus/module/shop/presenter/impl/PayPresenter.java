/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：PayPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/12/14:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.model.IOrderModel;
import com.tp.venus.module.shop.model.impl.OrderModel;
import com.tp.venus.module.shop.presenter.IPayPresenter;
import com.tp.venus.module.shop.ui.view.IPayView;

import java.util.Map;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.PayPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/12/14:23
 * @version 1.0.0
 */

public class PayPresenter extends BasePresenter implements IPayPresenter{

    private IPayView payView;
    private IOrderModel orderModel;

    public PayPresenter(IPayView mBaseView) {
        super(mBaseView);
        this.payView = mBaseView;
        this.orderModel = new OrderModel();
    }

    @Override
    public void pay(String orderId, int payType) {
        orderModel.pay(orderId, payType, new RxSubscriber<JsonMessage<Map<String, String>>>(payView) {
            @Override
            public void onSuccess(JsonMessage<Map<String, String>> message) {
                payView.onSuccess(message.getObj());
            }
        });
    }

    @Override
    public void paySuccess(String prepayId) {
        orderModel.paySuccess(prepayId, new RxSubscriber<JsonMessage>(payView) {
            @Override
            public void onSuccess(JsonMessage message) {
                payView.paySuccess();
            }
        });
    }
}
