/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：ShopCartPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/15:19.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import android.widget.EditText;

import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.model.ICartModel;
import com.tp.venus.module.shop.model.impl.CartModel;
import com.tp.venus.module.shop.presenter.IShopCartPresenter;
import com.tp.venus.module.shop.ui.view.IShopCartView;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.ShopCartPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/15:19
 * @version 1.0.0
 */

public class ShopCartPresenter extends BasePresenter implements IShopCartPresenter {

    private IShopCartView mIShopCartView;
    private ICartModel cartModel;

    public ShopCartPresenter(IShopCartView mBaseView) {
        super(mBaseView);
        this.mIShopCartView = mBaseView;
        this.cartModel = new CartModel();
    }

    @Override
    public void changeBuyCount(final EditText edit, final ShopCart product, final int buyCount) {
        cartModel.changeBuyCount(product.getId(), buyCount, new ProgressSubscriber<JsonMessage>(mIShopCartView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIShopCartView.changeBuyCount(edit, buyCount, product);
            }
        });
    }

    @Override
    public void delete(String itemIds, final int position, final CommonViewHolder mCommonViewHolder) {
        cartModel.delete(itemIds, new ProgressSubscriber<JsonMessage>(mIShopCartView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIShopCartView.delete(position, mCommonViewHolder);
            }
        });
    }

    @Override
    public void statement() {
        mIShopCartView.statement();
    }
}
