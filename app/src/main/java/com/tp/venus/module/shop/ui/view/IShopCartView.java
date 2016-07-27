/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IShopCartView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/16:08.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import android.widget.TextView;

import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.shop.bean.ShopCart;

/**<p>Class：com.tp.venus.module.shop.ui.view.IShopCartView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/16:08
 * @version 1.0.0
 */

public interface IShopCartView extends BaseView {

    /**
     * 选择购买数量
     * @param buyCount
     */
    void changeBuyCount(TextView edit, int buyCount, ShopCart product);

    /**
     *  删除某个商品
     * @param position
     */
    void delete(int position, CommonViewHolder mCommonViewHolder);

    /**
     * 结算
     */
    void statement();

}
