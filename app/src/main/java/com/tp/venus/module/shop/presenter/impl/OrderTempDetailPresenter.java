/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：OrderTempDetailPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/23/15:19.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.Address;
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.model.IAddressModel;
import com.tp.venus.module.shop.model.IOrderModel;
import com.tp.venus.module.shop.model.impl.AddressModel;
import com.tp.venus.module.shop.model.impl.OrderModel;
import com.tp.venus.module.shop.presenter.IOrderTempDetailPresenter;
import com.tp.venus.module.shop.ui.view.IOrderTempDetailView;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.OrderTempDetailPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/23/15:19
 * @version 1.0.0
 */

public class OrderTempDetailPresenter extends BasePresenter implements IOrderTempDetailPresenter {

    private IOrderTempDetailView mIOrderDetailView;
    private IAddressModel iAddressModel;
    private IOrderModel iOrderModel;



    public OrderTempDetailPresenter(IOrderTempDetailView mBaseView) {
        super(mBaseView);
        this.mIOrderDetailView = mBaseView;
        this.iAddressModel = new AddressModel();
        this.iOrderModel = new OrderModel();
    }

    @Override
    public void getCurrentUserAdress() {
        iAddressModel.getCurrentUseerAddress(new RxSubscriber<JsonMessage<Address>>(mIOrderDetailView) {
            @Override
            public void onSuccess(JsonMessage<Address> message) {
                mIOrderDetailView.showAddress(message.getObj());
            }
        });

    }

    @Override
    public void createOrder(String addressId, String itemIds, String message) {
        if(StringUtil.isEmpty(addressId)){
            mIOrderDetailView.showError("请先填写收货信息");
            return ;
        }
        if( StringUtil.isEmpty(itemIds)){
            mIOrderDetailView.showError("请选择您要购买的商品");
            return ;
        }
        iOrderModel.createOrder(addressId, itemIds, message, new ProgressSubscriber<JsonMessage<Order>>(mIOrderDetailView) {
            @Override
            public void onSuccess(JsonMessage<Order> message) {
                mIOrderDetailView.createOrderSuccess(message.getObj());
            }
        });
    }

    @Override
    public void calc(String itemIds) {
        iOrderModel.calc(itemIds, new RxSubscriber<JsonMessage<Order>>(mIOrderDetailView) {
            @Override
            public void onSuccess(JsonMessage<Order> message) {
                mIOrderDetailView.calc(message.getObj());
            }
        });
    }

    @Override
    public void buyNow(String addressId, String productId, String selectedSku, String message) {
        if(StringUtil.isEmpty(addressId)){
            mIOrderDetailView.showError("请先填写收货信息");
            return ;
        }
        if( StringUtil.isEmpty(productId)){
            mIOrderDetailView.showError("请选择您要购买的商品");
            return ;
        }
        iOrderModel.buyNow(addressId, productId, selectedSku, message, new ProgressSubscriber<JsonMessage<Order>>(mIOrderDetailView) {
            @Override
            public void onSuccess(JsonMessage<Order> message) {
                mIOrderDetailView.createOrderSuccess(message.getObj());
            }
        });
    }
}
