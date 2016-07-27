/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：CartCountPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/11/15:42.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.model.IOrderModel;
import com.tp.venus.module.shop.model.impl.OrderModel;
import com.tp.venus.module.shop.presenter.ICartCountPresenter;
import com.tp.venus.module.shop.ui.view.ICartCountView;
import com.tp.venus.util.StringUtil;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.CartCountPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/11/15:42
 * @version 1.0.0
 */

public class CartCountPresenter extends BasePresenter implements ICartCountPresenter{

    private ICartCountView mICartCountView;
    private IOrderModel orderModel;

    public CartCountPresenter(ICartCountView mBaseView) {
        super(mBaseView);
        this.mICartCountView = mBaseView;
        this.orderModel = new OrderModel();
    }

    @Override
    public void showBadgeView() {
        String token = getToken();
        if(StringUtil.isEmpty(token)){
            Observable.just("1")
            .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    mICartCountView.showBadgeView(null);
                }
            });
        } else {
            orderModel.showBadgeView(new RxSubscriber<JsonMessage<Map<String, Integer>>>(mICartCountView) {
                @Override
                public void onSuccess(JsonMessage<Map<String, Integer>> message) {
                    mICartCountView.showBadgeView(message.getObj().get("sum"));
                }
            });
        }

    }
}
