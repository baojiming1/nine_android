/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：OrderBottonPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/6/14:25.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import android.content.DialogInterface;

import com.orhanobut.dialogplus.DialogPlus;
import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.model.impl.OrderModel;
import com.tp.venus.module.shop.presenter.IOrderBottonPresenter;
import com.tp.venus.module.shop.ui.view.IOderBottonView;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.OrderBottonPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/6/14:25
 * @version 1.0.0
 */

public class OrderBottonPresenter extends BasePresenter implements IOrderBottonPresenter {

    private IOderBottonView mIOderBottonView;
    private OrderModel orderModel;

    public OrderBottonPresenter(IOderBottonView mBaseView) {
        super(mBaseView);
        this.mIOderBottonView = mBaseView;
        this.orderModel = new OrderModel();
    }

    @Override
    public void updateOrderStatus(String orderId, final int orderStatus, final DialogInterface dialog, final int position) {
        orderModel.updateOrderStatus(orderId, orderStatus, new ProgressSubscriber<JsonMessage<Order>>(mIOderBottonView) {
            @Override
            public void onSuccess(JsonMessage<Order> message) {
                mIOderBottonView.updateOrderStatusSuccess(message.getObj(), position);
            }
            @Override
            public void onCompleted() {
                dialog.dismiss();
                super.onCompleted();
            }
            @Override
            public void onFail(JsonMessage jsonMessage) {
                dialog.dismiss();
                super.onFail(jsonMessage);
            }
        });
    }

    @Override
    public void save_aftersale_service_info(String orderId, String phone, String remark, final DialogPlus mDialogPlus, final int position) {
        if(StringUtil.isEmpty(phone)){
            ToastUtil.getInstance().show("请填写您的联系方式，方便我们及时的能与您沟通");
            return ;
        }
        orderModel.save_aftersale_service_info(orderId, phone, remark, new ProgressSubscriber<JsonMessage<Order>>(mIOderBottonView) {
            @Override
            public void onSuccess(JsonMessage<Order> message) {
                mIOderBottonView.saveServiceInfoSuccess(message.getObj(), position);
            }

            @Override
            public void onCompleted() {
                mDialogPlus.dismiss();
                super.onCompleted();
            }

            @Override
            public void onNext(JsonMessage<Order> o) {
                mDialogPlus.dismiss();
                super.onNext(o);
            }

        });
    }
}
