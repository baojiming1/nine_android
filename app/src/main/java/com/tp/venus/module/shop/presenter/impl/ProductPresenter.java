/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：ProductPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/28/14:46.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import com.tp.venus.base.mvp.p.BaseRefreshPresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.model.IFavoriteModel;
import com.tp.venus.module.common.model.imp.FavoriteModel;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.model.ICartModel;
import com.tp.venus.module.shop.model.IProductModel;
import com.tp.venus.module.shop.model.impl.CartModel;
import com.tp.venus.module.shop.model.impl.ProductModel;
import com.tp.venus.module.shop.presenter.IProductPresenter;
import com.tp.venus.module.shop.ui.view.IProductView;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.ProductPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/28/14:46
 * @version 1.0.0
 */

public class ProductPresenter extends BaseRefreshPresenter implements IProductPresenter {
    private IProductView mIProductView;
    private IProductModel mIProductModel;
    private ICartModel cartModel;
    private IFavoriteModel favoriteModel;

    public ProductPresenter(IProductView mIProductView) {
        super(mIProductView);
        this.mIProductView = mIProductView;
        this.mIProductModel = new ProductModel();
        this.cartModel = new CartModel();
        this.favoriteModel = new FavoriteModel();
    }

    @Override
    public void showProductDetail(String productId) {
        if(StringUtil.isEmpty(productId)){
            return ;
        }
        mIProductModel.getInfo(productId, new RxSubscriber<JsonMessage<Product>>(mIProductView) {
            @Override
            public void onSuccess(JsonMessage<Product> message) {
                mIProductView.showProductDetail(message.getObj());
            }
        });
    }

    @Override
    public void addCart(String productId, Integer buyCount, String sku) {
        if( StringUtil.isEmpty(productId)){
            mIProductView.showError("产品编号不能为空");
            return ;
        }
        if( buyCount <= 0){
            mIProductView.showError("购买的数量不正确");
            return ;
        }
        cartModel.addCart(productId, buyCount, sku, new RxSubscriber<JsonMessage>(mIProductView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIProductView.addCartSuccess();
            }
        });
    }

    @Override
    public void favorite(String productId, final boolean isFavorite) {
        favoriteModel.favorite(productId, isFavorite, Status.Favorite.PRODUCT, new ProgressSubscriber<JsonMessage>(mIProductView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIProductView.favoriteSuccess(isFavorite);
            }
        });
    }

    @Override
    public void buyNow(Product mProduct, String sku, int buyCount) {
        ShopCart simpleProduct = new ShopCart();
        simpleProduct.setId("");
        simpleProduct.setBuyCount(buyCount);
        simpleProduct.setFreight(mProduct.getFreight());
        simpleProduct.setMainImage(mProduct.getMainImage());
        simpleProduct.setOriginArea(mProduct.getOriginArea());
        simpleProduct.setPrice(mProduct.getPrice());
        simpleProduct.setRealPrice(mProduct.getRealPrice());
        simpleProduct.setTitle(mProduct.getTitle());
        simpleProduct.setUserId(mProduct.getUserId());
        simpleProduct.setSelectedSku(sku);
        mIProductView.buyNowSuccess(simpleProduct);
    }
}
