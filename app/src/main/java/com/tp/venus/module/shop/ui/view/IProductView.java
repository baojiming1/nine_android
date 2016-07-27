/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IProductView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/28/14:47.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import com.tp.venus.base.mvp.v.BaseRefreshView;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.bean.ShopCart;

/**<p>Class：com.tp.venus.module.shop.ui.view.IProductView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/28/14:47
 * @version 1.0.0
 */

public interface IProductView  extends BaseRefreshView<Product> {

    /**
     * 显示详情信息
     */
    void showProductDetail(Product mProduct);


    /**
     * 添加购物车成功
     */
    void addCartSuccess();

    /**
     * 收藏成功
     */
    void favoriteSuccess(boolean isFavorite);

    /**
     *  立即购买成功
     */
    void buyNowSuccess(ShopCart simpleProduct);
}
