/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IProductPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/28/14:42.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBaseRefreshPresenter;
import com.tp.venus.module.shop.bean.Product;

/**<p>Class：com.tp.venus.module.shop.presenter.IProductPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/28/14:42
 * @version 1.0.0
 */

public interface IProductPresenter extends IBaseRefreshPresenter {

    /**
     * 显示产品详情信息
     * @param productId
     */
    void showProductDetail(String productId);

    /**
     * 加入到购物车
     * @param productId
     * @param buyCount
     * @param sku
     */
    @Login
    void addCart(String productId, Integer buyCount, String sku);


    /**
     * 收藏商品
     * @param productId
     * @param isFavorite
     */
    @Login
    void favorite(String productId, boolean isFavorite);

    /**
     * 立即购买
     * @param mProduct
     */
    @Login
    void buyNow(Product mProduct, String sku, int buyCount);
}
